package com.team.noty.event.dagger2.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;

import com.google.gson.Gson;
import com.team.noty.event.app.App;
import com.team.noty.event.convertors.Converter;
import com.team.noty.event.dagger2.modules.AppModule;
import com.team.noty.event.dagger2.modules.RegLogModule;
import com.team.noty.event.dagger2.modules.RetrofitModule;
import com.team.noty.event.dagger2.modules.SingletonModule;
import com.team.noty.event.interceptors.AsyncList;
import com.team.noty.event.repository.cache.Cache;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.models.User;
import com.team.noty.event.mvp.presenters.FilterPresenter;
import com.team.noty.event.mvp.presenters.IntroPresenter;
import com.team.noty.event.mvp.presenters.LoginPresenter;
import com.team.noty.event.mvp.presenters.MapPresenter;
import com.team.noty.event.mvp.presenters.RegistrationPresenter;
import com.team.noty.event.mvp.presenters.RestorePresenter;
import com.team.noty.event.mvp.presenters.SelectAddressPresenter;
import com.team.noty.event.mvp.presenters.SelectCityPresenter;
import com.team.noty.event.mvp.presenters.SelectLocationPresenter;
import com.team.noty.event.network.api.ChatApi;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsAct;
import com.team.noty.event.ui.activities.AccessRecoveryActivity;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.ContactsProvider;
import com.team.noty.event.utils.ErrorHandler;
import com.team.noty.event.utils.PermissionController;

import java.util.List;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class, RegLogModule.class, SingletonModule.class})
public interface AppComponent {
    Context context();

    DataProvide<List<Category>> generalCategoryProvider();

    ContentApi contentApi();

    ChatApi chatApi();

    Retrofit retrofit();

    AsyncList<ActId> banedList();

    retrofit2.Converter.Factory converterFactory();

    Gson gson();

    ContactsProvider provider();

    AccountPreferenceManager accountPreferenceManager();

    PermissionController permissionController();

    ErrorHandler errorHandler();

    Resources resources();

    Interactor<String, Act> shappingDateInteractor();

    Interactor<Single<Category>, String> categoryById();

    MediaPlayer mediaPlayer();

    Converter<Act, RsAct> actConverter();

    Interactor<Observable<Act>, FilterModel> filter();

    Cache<ActId, Act> actCache();

    Cache<Integer, User> userCache();

    Interactor<Single<Bitmap>, String> imageLoader();

    void inject(App application);

    void inject(LoginPresenter presenter);

    void inject(SelectAddressPresenter presenter);

    void inject(IntroPresenter presenter);

    void inject(RegistrationPresenter presenter);

    void inject(SelectCityPresenter placesTask);

    void inject(FilterPresenter presenter);

    void inject(SelectLocationPresenter presenter);

    void inject(MapPresenter presenter);

    void inject(RestorePresenter presenter);

    void inject(AccessRecoveryActivity presenter);
}
