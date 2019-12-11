package com.team.noty.event.utils.media.play;

import android.media.MediaPlayer;

import com.team.noty.event.utils.Log;
import com.team.noty.event.utils.PermissionController;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AudioPlayerImpl implements AudioPlayer {
    private MediaPlayer player;
    private PermissionController permissionController;
    private Disposable disposable;
    private int duration = -1;
    private String path;

    @Inject
    public AudioPlayerImpl(MediaPlayer player, PermissionController permissionController) {
        this.player = player;
        this.permissionController = permissionController;
    }

    @Override
    public boolean onPlay() {
        return path!=null;
    }

    @Override
    public int duration() {
        return duration / 1000;
    }

    @Override
    public void onResumePause() {
        player.reset();
        if (player.isPlaying())
            player.pause();
        else
            player.start();
    }

    @Override
    public Observable<Integer> play(String path) {
        return permissionController.storage().andThen(start(path));
    }

    private Observable<Integer> start(String path) {

        this.path = path;
        return Observable.create(this::prepare)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> this.disposable = disposable)
                .doOnDispose(this::stop);
    }

    private void prepare(ObservableEmitter<Integer> e){
        try {
            player.reset();
            player.setDataSource(this.path);
            player.setOnPreparedListener(new PreparedListener(e));
            player.setOnCompletionListener(new PreparedListener(e));
            player.prepareAsync();
        } catch (Exception ex) {
            e.onError(ex);
        }
    }

    @Override
    public void stop() {
        if(player==null)
            if (player.isPlaying()) {
                player.reset();
                player.stop();

            }
            if (disposable != null && !disposable.isDisposed())
                disposable.dispose();
            path = null;
    }

    class PreparedListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

        ObservableEmitter<Integer> emitter;

        PreparedListener(ObservableEmitter<Integer> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            player.start();
            duration = player.getDuration();
            new Thread(() -> {
                int prew = 0;
                while (!emitter.isDisposed() && prew <= duration) {
                    //android.util.Log.i("onPrepare", "1");
                    if (player.isPlaying() && prew < (player.getCurrentPosition() / 1000)) {
                        android.util.Log.i("onPrepare", "2");
                        emitter.onNext(prew + 1);
                        prew = player.getCurrentPosition() / 1000;
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e1) {
                        emitter.onError(e1);
                        Thread.currentThread().interrupt();
                    }
                }

            }).start();
        }

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            emitter.onComplete();
            stop();
        }
    }
}
