package evfor.fun.skvader.mvp.presenters;

import evfor.fun.skvader.mvp.views.BaseView;
import evfor.fun.skvader.utils.EventBus;

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
