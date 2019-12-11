package evfor.fun.skvader.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.models.NotificationCount;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.views.TabbetView;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.Updater;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
@SuppressLint("CheckResult")
public class TabsPresenter extends BaseEventBusPresenter<TabbetView> {

    @Inject
    ReaderRepos<User, Integer> userReader;
    @Inject
    Updater<User, Integer> userUpdater;
    @Inject
    DataProvide<NotificationCount> notificationCountProvide;

    public TabsPresenter() {
        Injector.get().getMain().inject(this);
    }

    private Single<User> getUser() {
        return userReader.request(AuthData.getIDInt())
                .doOnSuccess(user -> AuthData.city = user.city)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void loadProfile() {
        getUser().subscribe(this::setUser, this::onError);
    }
    public void  updateNotificationCount(){
//        notificationCountProvide.provide()
//                .subscribe(EventBus::post, this::onError);
    }

    public void updateProfile() {
        userUpdater.update(AuthData.getIDInt(), this::updateUser).subscribe();
    }

    private void updateUser(User user) {
        AuthData.id = String.valueOf(user.id);
        Injector.get().getApp().accountPreferenceManager().saveFromData();
        //EventBus.post(user);
    }

    private void setUser(User user) {
        updateUser(user);
        getViewState().onComplete();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setNotCount(NotificationCount notCount) {
        int notyCount = StringUtils.toIntOr0(notCount.count);
        if (notCount.addCount == 0)
            getViewState().setNotificationCount(notCount.count);
        else getViewState().setNotificationCount(String.valueOf(notyCount + notCount.addCount));
    }
}
