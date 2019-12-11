package evfor.fun.skvader.dagger2.modules;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.media.MediaPlayer;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import evfor.fun.skvader.app.App;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import evfor.fun.skvader.utils.ContactsProvider;
import evfor.fun.skvader.utils.PermissionController;

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
