package com.team.noty.event.interceptors;

import io.reactivex.Completable;

public interface AsyncList<T> {
    Completable contain(T t);
    Completable add(T t);
    Completable remove(T t);
}
