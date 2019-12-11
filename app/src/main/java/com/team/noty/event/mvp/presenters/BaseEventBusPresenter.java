package com.team.noty.event.mvp.presenters;

import com.team.noty.event.mvp.views.BaseView;
import com.team.noty.event.utils.EventBus;

public abstract class BaseEventBusPresenter<T extends BaseView> extends BasePresenter<T> {
    @Override
    public void attachView(T view) {
        super.attachView(view);
        EventBus.register(this);
    }

    @Override
    public void detachView(T view) {
        super.detachView(view);
        EventBus.unregister(this);
    }
}
