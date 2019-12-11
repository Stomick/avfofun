package evfor.fun.skvader.utils.media.record;

import java.io.File;

import io.reactivex.Single;

public interface AudioRecorder {
    Single<File> start();
    void end();
    void cancel();
    boolean isRec();
}
