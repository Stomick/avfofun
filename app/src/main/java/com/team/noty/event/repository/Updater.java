package com.team.noty.event.repository;

import com.team.noty.event.utils.callbacks.CallBack1;

import io.reactivex.Completable;

public interface Updater<What, By> {
    Completable update(By by, CallBack1<What> updating);
}
