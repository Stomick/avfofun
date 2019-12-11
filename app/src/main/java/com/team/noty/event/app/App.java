package com.team.noty.event.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pacoworks.rxpaper2.RxPaperBook;
import com.team.noty.event.R;
import com.team.noty.event.network.api.LoginApi;
import com.team.noty.event.network.models.request.RqFBToken;
import com.team.noty.event.services.MessagesService;
import com.team.noty.event.services.TokenRefresh;
import com.team.noty.event.utils.EventBus;
import com.team.noty.event.utils.notification.NotificationsUtils;
import com.vk.sdk.VKSdk;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {

    private static App INSTANSE;
    @Inject
    LoginApi api;
    private Disposable sendingToken;

    public App() {
        super();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANSE = this;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(INSTANSE)
                .build();
        ImageLoader.getInstance().init(config);
        Injector.createInstance(INSTANSE);
        RxPaperBook.init(INSTANSE);
        Injector.get().getApp().inject(this);
        createNotificationChannel();
        NotificationsUtils.create();
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        VKSdk.initialize(this);

        //startService(new Intent(this,MessagesService.class));
        //startService(new Intent(this,TokenRefresh.class));
    }

    public static App getINSTANSE() {
        return INSTANSE;
    }

    public void sendFBToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (AuthData.checkToken() && token != null) {
            if (sendingToken != null && !sendingToken.isDisposed())
                sendingToken.dispose();
            sendingToken = api.sendFBToken(AuthData.getToken(), new RqFBToken(token))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(mode -> {
                        Log.e("my", String.valueOf(mode.error));},
                            throwable -> {Log.e("my",throwable.getMessage());});
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(channel);
        }
    }
}
