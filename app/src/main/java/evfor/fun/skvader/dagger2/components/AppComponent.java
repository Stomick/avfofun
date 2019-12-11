package evfor.fun.skvader.dagger2.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;

import com.google.gson.Gson;
import evfor.fun.skvader.app.App;
import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.dagger2.modules.AppModule;
import evfor.fun.skvader.dagger2.modules.RegLogModule;
import evfor.fun.skvader.dagger2.modules.RetrofitModule;
import evfor.fun.skvader.dagger2.modules.SingletonModule;
import evfor.fun.skvader.interceptors.AsyncList;
import evfor.fun.skvader.repository.cache.Cache;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.presenters.FilterPresenter;
import evfor.fun.skvader.mvp.presenters.IntroPresenter;
import evfor.fun.skvader.mvp.presenters.LoginPresenter;
import evfor.fun.skvader.mvp.presenters.MapPresenter;
import evfor.fun.skvader.mvp.presenters.RegistrationPresenter;
import evfor.fun.skvader.mvp.presenters.RestorePresenter;
import evfor.fun.skvader.mvp.presenters.SelectAddressPresenter;
import evfor.fun.skvader.mvp.presenters.SelectCityPresenter;
import evfor.fun.skvader.mvp.presenters.SelectLocationPresenter;
import evfor.fun.skvader.network.api.ChatApi;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.ui.activities.AccessRecoveryActivity;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import evfor.fun.skvader.utils.ContactsProvider;
import evfor.fun.skvader.utils.ErrorHandler;
import evfor.fun.skvader.utils.PermissionController;

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
