package com.team.noty.event.utils.callbacks;

public interface CallBack {
    void call();

    CallBack empty = () -> {};
}
