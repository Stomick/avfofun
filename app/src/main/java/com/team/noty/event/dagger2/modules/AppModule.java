package com.team.noty.event.dagger2.modules;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.team.noty.event.app.App;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.ContactsProvider;
import com.team.noty.event.utils.PermissionController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Resources providerResources(Context context){
        return context.getResources();
    }

    @Provides
    @Singleton
    AccountPreferenceManager provideAccountPrefManager(Context context) {
        return new AccountPreferenceManager(context.getSharedPreferences(AccountPreferenceManager.TAG, Context.MODE_PRIVATE));
    }

    @Singleton
    @Provides
    ContactsProvider provider(Context context){
        return new ContactsProvider(context);
    }

    @Singleton
    @Provides
    PermissionController providePermissionController(Context context){
        return new PermissionController(context);
    }

    @Singleton
    @Provides
    LocationManager provideLocationManager(Context context){
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    MediaPlayer provideMediaPlayer(){
        return new MediaPlayer();
    }

    @Provides
    @Singleton
    FusedLocationProviderClient fusedLocationClient(Context context){
        return LocationServices.getFusedLocationProviderClient(context);
    }
}
