package evfor.fun.skvader.repository;

import evfor.fun.skvader.utils.callbacks.CallBack1;

import io.reactivex.Completable;

public interface Updater<What, By> {
    Completable update(By by, CallBack1<What> updating);
}
