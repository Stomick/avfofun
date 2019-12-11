package com.team.noty.event.utils.callbacks;

public interface CallBack1<T> {
    void call(T t);

    static <S> CallBack1<S> empty(){
        return s -> {};
    }
}
