package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.NotificationCount;
import com.team.noty.event.models.NotificationFilter;
import com.team.noty.event.mvp.views.NotificationView;
import com.team.noty.event.models.Notification;
import com.team.noty.event.utils.EventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class NotificationPresenter extends BaseEventBusPresenter<NotificationView> {

    @Inject
    Interactor<Observable<Notification>, NotificationFilter> notificationProvider;

    private NotificationFilter filter = NotificationFilter.ALL;

    public NotificationPresenter() {
        Injector.get().getMain().inject(this);
    }

    public void loadList(NotificationFilter filter) {
        this.filter = filter;
        notificationProvider.call(filter)
                .toSortedList()
                //.doAfterSuccess(uiNotifications -> EventBus.post(filter))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showList, Throwable::printStackTrace);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addNotification(Notification notification) {
        loadList(filter);
    }
}



