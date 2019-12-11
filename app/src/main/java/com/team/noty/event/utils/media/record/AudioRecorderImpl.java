package com.team.noty.event.utils.media.record;

import android.media.MediaRecorder;
import android.os.Environment;

import com.team.noty.event.utils.PermissionController;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.schedulers.Schedulers;

public class AudioRecorderImpl implements AudioRecorder {
    private MediaRecorder recorder;
    private PermissionController permissionController;
    private String path;
    private static final int MAX_SEC = 180;
    private SingleEmitter<File> emitter;
    private final String format = ".wav";

    @Inject
    AudioRecorderImpl(PermissionController permissionController) {
        this.permissionController = permissionController;
    }

    private void init() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile(path);
        recorder.setMaxDuration(MAX_SEC * 1000);
    }

    private File result() {
        return new File(path);
    }

    private void createPath() {
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + createVoiceFile() + format;
    }

    private String createVoiceFile() {
        return File.separator + "rec_" + System.nanoTime();
    }

    @Override
    public Single<File> start() {
        createPath();
        return permissionController.recordAudio()
                .subscribeOn(Schedulers.io())
                .andThen(Single.create(this::create)
                        .subscribeOn(Schedulers.io())
                        .doOnDispose(this::cancel));
    }

    private void create(SingleEmitter<File> emitter) {
        if (recorder == null) {
            this.emitter = emitter;
            try {
                init();
                recorder.prepare();
                recorder.start();
                recorder.setOnInfoListener(this::statusListener);
                recorder.setOnErrorListener(this::statusListener);
            } catch (Exception ex) {
                emitter.onError(ex);
                release();
            }
        }
    }

    private void statusListener(MediaRecorder mr, int what, int extras) {
        switch (what) {
            case MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED:
                emitter.onSuccess(result());
                release();
                break;
            case MediaRecorder.MEDIA_RECORDER_ERROR_UNKNOWN:
                emitter.onError(new Exception("Unspecified media recorder error"));
                break;
            case MediaRecorder.MEDIA_ERROR_SERVER_DIED:
                emitter.onError(new Exception("Media server died"));
                break;
        }
    }

    @Override
    public void end() {
        try {
            if (recorder != null) {
                recorder.stop();
                emitter.onSuccess(result());
            }
        } catch (RuntimeException ex) {
            emitter.onError(ex);
        } finally {
            release();
        }
    }

    @Override
    public void cancel() {
        try {
            if (recorder != null) {
                recorder.stop();
                result().delete();
                emitter.onError(new CancelRecordException());
            }
        } catch (RuntimeException ex) {
            emitter.onError(ex);
        } finally {
            release();
        }
    }

    private void release() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }
    }

    @Override
    public boolean isRec() {
        return recorder != null;
    }

    public class CancelRecordException extends Exception {
    }
}
