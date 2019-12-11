package com.team.noty.event.utils.media.record;

import java.io.File;

import io.reactivex.Single;

public interface AudioRecorder {
    Single<File> start();
    void end();
    void cancel();
    boolean isRec();
}
