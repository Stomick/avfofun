package com.team.noty.event.utils.media.play;

import io.reactivex.Observable;

public interface AudioPlayer {
    Observable<Integer> play(String path);
    void onResumePause();
    void stop();
    int duration();
    boolean onPlay();
}
