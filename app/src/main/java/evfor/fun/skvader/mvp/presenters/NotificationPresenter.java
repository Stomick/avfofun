package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.NotificationFilter;
import evfor.fun.skvader.mvp.views.NotificationView;
import evfor.fun.skvader.models.Notification;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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



