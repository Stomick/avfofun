// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.dagger2.components;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import evfor.fun.skvader.app.App;
import evfor.fun.skvader.app.App_MembersInjector;
import evfor.fun.skvader.convertors.ActConverter;
import evfor.fun.skvader.convertors.ActConverter_Factory;
import evfor.fun.skvader.convertors.AddressConverter_Factory;
import evfor.fun.skvader.dagger2.modules.AppModule;
import evfor.fun.skvader.dagger2.modules.AppModule_FusedLocationClientFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProvideAccountPrefManagerFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProvideApplicationContextFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProvideMediaPlayerFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProvidePermissionControllerFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProviderFactory;
import evfor.fun.skvader.dagger2.modules.AppModule_ProviderResourcesFactory;
import evfor.fun.skvader.dagger2.modules.RegLogModule;
import evfor.fun.skvader.dagger2.modules.RegLogModule_ProvideLoginApiFactory;
import evfor.fun.skvader.dagger2.modules.RegLogModule_ProvideRegistrationApiFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ContentApiFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideChatApiFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideConverterFactoryFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideGsonFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideOkHttpFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideRetrofitBuilderFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideRetrofitFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideRxCallAdapterFactory;
import evfor.fun.skvader.dagger2.modules.RetrofitModule_ProvideRxJava2CallAdapterFactoryFactory;
import evfor.fun.skvader.dagger2.modules.SingletonModule_ProvideCacheOptionsFactory;
import evfor.fun.skvader.interceptors.ActByLocationInteractor;
import evfor.fun.skvader.interceptors.ActByLocationInteractor_Factory;
import evfor.fun.skvader.interceptors.AsyncList;
import evfor.fun.skvader.interceptors.BanedActList_Factory;
import evfor.fun.skvader.interceptors.CategoryById;
import evfor.fun.skvader.interceptors.CategoryById_Factory;
import evfor.fun.skvader.interceptors.CityProvider;
import evfor.fun.skvader.interceptors.CityProvider_Factory;
import evfor.fun.skvader.interceptors.FilterInteractor;
import evfor.fun.skvader.interceptors.FilterInteractor_Factory;
import evfor.fun.skvader.interceptors.ImprovementPlace;
import evfor.fun.skvader.interceptors.ImprovementPlace_Factory;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.interceptors.LoadImageInteractor_Factory;
import evfor.fun.skvader.interceptors.ShapingDateInteractor;
import evfor.fun.skvader.interceptors.ShapingDateInteractor_Factory;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.mvp.presenters.FilterPresenter;
import evfor.fun.skvader.mvp.presenters.IntroPresenter;
import evfor.fun.skvader.mvp.presenters.IntroPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.LoginPresenter;
import evfor.fun.skvader.mvp.presenters.LoginPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.MapPresenter;
import evfor.fun.skvader.mvp.presenters.MapPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.RegistrationPresenter;
import evfor.fun.skvader.mvp.presenters.RegistrationPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.RestorePresenter;
import evfor.fun.skvader.mvp.presenters.RestorePresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.SelectAddressPresenter;
import evfor.fun.skvader.mvp.presenters.SelectAddressPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.SelectCityPresenter;
import evfor.fun.skvader.mvp.presenters.SelectCityPresenter_MembersInjector;
import evfor.fun.skvader.mvp.presenters.SelectLocationPresenter;
import evfor.fun.skvader.mvp.presenters.SelectLocationPresenter_MembersInjector;
import evfor.fun.skvader.network.api.ChatApi;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.network.api.RegistrationApi;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.network.models.response.RsPlaces;
import evfor.fun.skvader.repository.cache.Cache;
import evfor.fun.skvader.repository.cache.CacheMap;
import evfor.fun.skvader.repository.cache.CacheMap_Factory;
import evfor.fun.skvader.repository.cache.CacheOptions;
import evfor.fun.skvader.repository.data_observe.DataObserver;
import evfor.fun.skvader.repository.data_observe.FusedLocationObserver;
import evfor.fun.skvader.repository.data_observe.FusedLocationObserver_Factory;
import evfor.fun.skvader.repository.data_provide.CategoryListProvide;
import evfor.fun.skvader.repository.data_provide.CategoryListProvide_Factory;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.repository.data_provide.DataProvide1;
import evfor.fun.skvader.repository.data_provide.LastLocationProvide;
import evfor.fun.skvader.repository.data_provide.LastLocationProvide_Factory;
import evfor.fun.skvader.repository.data_provide.PlaceByLocProvider;
import evfor.fun.skvader.repository.data_provide.PlaceByLocProvider_Factory;
import evfor.fun.skvader.repository.data_provide.PlaceByWordProvider;
import evfor.fun.skvader.repository.data_provide.PlaceByWordProvider_Factory;
import evfor.fun.skvader.ui.activities.AccessRecoveryActivity;
import evfor.fun.skvader.ui.activities.AccessRecoveryActivity_MembersInjector;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import evfor.fun.skvader.utils.ContactsProvider;
import evfor.fun.skvader.utils.ErrorHandler;
import evfor.fun.skvader.utils.ErrorHandler_Factory;
import evfor.fun.skvader.utils.PermissionController;
import evfor.fun.skvader.utils.RxCallAdapter;
import evfor.fun.skvader.utils.iHandler;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

public final class DaggerAppComponent implements AppComponent {
  private Provider<Context> provideApplicationContextProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<Converter.Factory> provideConverterFactoryProvider;

  private Provider<OkHttpClient> provideOkHttpProvider;

  private Provider<RxJava2CallAdapterFactory> provideRxJava2CallAdapterFactoryProvider;

  private Provider<Resources> providerResourcesProvider;

  private Provider<ErrorHandler> errorHandlerProvider;

  private Provider<iHandler<Throwable>> providesErrorHandlerProvider;

  private Provider<RxCallAdapter> provideRxCallAdapterProvider;

  private Provider<Retrofit.Builder> provideRetrofitBuilderProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<ContentApi> contentApiProvider;

  private Provider<CategoryListProvide> categoryListProvideProvider;

  private Provider<DataProvide<List<Category>>> provideListCategoryProvider;

  private Provider<ChatApi> provideChatApiProvider;

  private Provider<AsyncList<ActId>> provideBanedListProvider;

  private Provider<ContactsProvider> providerProvider;

  private Provider<AccountPreferenceManager> provideAccountPrefManagerProvider;

  private Provider<PermissionController> providePermissionControllerProvider;

  private Provider<ShapingDateInteractor> shapingDateInteractorProvider;

  private Provider<Interactor<String, Act>> provideShappingDateInteractorProvider;

  private Provider<CategoryById> categoryByIdProvider;

  private Provider<Interactor<Single<Category>, String>> provideCategoryByIdProvider;

  private Provider<MediaPlayer> provideMediaPlayerProvider;

  private Provider<ActConverter> actConverterProvider;

  private Provider<evfor.fun.skvader.convertors.Converter<Act, RsAct>> provideConverterProvider;

  private Provider<FilterInteractor> filterInteractorProvider;

  private Provider<Interactor<Observable<Act>, FilterModel>> provideFilterProvider;

  private Provider<CacheOptions> provideCacheOptionsProvider;

  private Provider<CacheMap<ActId, Act>> cacheMapProvider;

  private Provider<Cache<ActId, Act>> provideActCacheProvider;

  private Provider<CacheMap<Integer, User>> cacheMapProvider2;

  private Provider<Cache<Integer, User>> provideUserCacheProvider;

  private Provider<Interactor<Single<Bitmap>, String>> provideImageLoaderProvider;

  private Provider<LoginApi> provideLoginApiProvider;

  private MembersInjector<App> appMembersInjector;

  private MembersInjector<LoginPresenter> loginPresenterMembersInjector;

  private Provider<evfor.fun.skvader.convertors.Converter<Address, RsPlaces.Places>>
      provideAddressConverterProvider;

  private Provider<ImprovementPlace> improvementPlaceProvider;

  private Provider<Interactor<Single<Address>, Address>> improvementPlaceProvider2;

  private Provider<PlaceByLocProvider> placeByLocProvider;

  private Provider<DataProvide1<List<Address>, Location>> placeByLocProvider2;

  private Provider<PlaceByWordProvider> placeByWordProvider;

  private Provider<DataProvide1<List<Address>, String>> placeByWordProvider2;

  private MembersInjector<SelectAddressPresenter> selectAddressPresenterMembersInjector;

  private MembersInjector<IntroPresenter> introPresenterMembersInjector;

  private Provider<RegistrationApi> provideRegistrationApiProvider;

  private MembersInjector<RegistrationPresenter> registrationPresenterMembersInjector;

  private Provider<CityProvider> cityProvider;

  private Provider<Interactor<Observable<City>, RqCity>> cityProvider2;

  private MembersInjector<SelectCityPresenter> selectCityPresenterMembersInjector;

  private Provider<FusedLocationProviderClient> fusedLocationClientProvider;

  private Provider<FusedLocationObserver> fusedLocationObserverProvider;

  private Provider<DataObserver<Location>> provideLocationProvider;

  private MembersInjector<SelectLocationPresenter> selectLocationPresenterMembersInjector;

  private Provider<ActByLocationInteractor> actByLocationInteractorProvider;

  private Provider<Interactor<Observable<Act>, Location>> provideActByLocationProvider;

  private Provider<LastLocationProvide> lastLocationProvideProvider;

  private Provider<DataProvide<Location>> lastLocationProvideProvider2;

  private MembersInjector<MapPresenter> mapPresenterMembersInjector;

  private MembersInjector<RestorePresenter> restorePresenterMembersInjector;

  private MembersInjector<AccessRecoveryActivity> accessRecoveryActivityMembersInjector;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideApplicationContextProvider =
        DoubleCheck.provider(AppModule_ProvideApplicationContextFactory.create(builder.appModule));

    this.provideGsonProvider = DoubleCheck.provider(RetrofitModule_ProvideGsonFactory.create());

    this.provideConverterFactoryProvider =
        DoubleCheck.provider(
            RetrofitModule_ProvideConverterFactoryFactory.create(provideGsonProvider));

    this.provideOkHttpProvider = DoubleCheck.provider(RetrofitModule_ProvideOkHttpFactory.create());

    this.provideRxJava2CallAdapterFactoryProvider =
        DoubleCheck.provider(RetrofitModule_ProvideRxJava2CallAdapterFactoryFactory.create());

    this.providerResourcesProvider =
        DoubleCheck.provider(
            AppModule_ProviderResourcesFactory.create(
                builder.appModule, provideApplicationContextProvider));

    this.errorHandlerProvider = ErrorHandler_Factory.create(providerResourcesProvider);

    this.providesErrorHandlerProvider = DoubleCheck.provider((Provider) errorHandlerProvider);

    this.provideRxCallAdapterProvider =
        DoubleCheck.provider(
            RetrofitModule_ProvideRxCallAdapterFactory.create(
                provideRxJava2CallAdapterFactoryProvider, providesErrorHandlerProvider));

    this.provideRetrofitBuilderProvider =
        DoubleCheck.provider(
            RetrofitModule_ProvideRetrofitBuilderFactory.create(
                provideConverterFactoryProvider,
                provideOkHttpProvider,
                provideRxCallAdapterProvider));

    this.provideRetrofitProvider =
        DoubleCheck.provider(
            RetrofitModule_ProvideRetrofitFactory.create(provideRetrofitBuilderProvider));

    this.contentApiProvider =
        DoubleCheck.provider(RetrofitModule_ContentApiFactory.create(provideRetrofitProvider));

    this.categoryListProvideProvider = CategoryListProvide_Factory.create(contentApiProvider);

    this.provideListCategoryProvider = DoubleCheck.provider((Provider) categoryListProvideProvider);

    this.provideChatApiProvider =
        DoubleCheck.provider(
            RetrofitModule_ProvideChatApiFactory.create(provideRetrofitBuilderProvider));

    this.provideBanedListProvider = DoubleCheck.provider((Provider) BanedActList_Factory.create());

    this.providerProvider =
        DoubleCheck.provider(
            AppModule_ProviderFactory.create(builder.appModule, provideApplicationContextProvider));

    this.provideAccountPrefManagerProvider =
        DoubleCheck.provider(
            AppModule_ProvideAccountPrefManagerFactory.create(
                builder.appModule, provideApplicationContextProvider));

    this.providePermissionControllerProvider =
        DoubleCheck.provider(
            AppModule_ProvidePermissionControllerFactory.create(
                builder.appModule, provideApplicationContextProvider));

    this.shapingDateInteractorProvider =
        ShapingDateInteractor_Factory.create(providerResourcesProvider);

    this.provideShappingDateInteractorProvider =
        DoubleCheck.provider((Provider) shapingDateInteractorProvider);

    this.categoryByIdProvider = CategoryById_Factory.create(contentApiProvider);

    this.provideCategoryByIdProvider = DoubleCheck.provider((Provider) categoryByIdProvider);

    this.provideMediaPlayerProvider =
        DoubleCheck.provider(AppModule_ProvideMediaPlayerFactory.create(builder.appModule));

    this.actConverterProvider = ActConverter_Factory.create(providerResourcesProvider);

    this.provideConverterProvider = DoubleCheck.provider((Provider) actConverterProvider);

    this.filterInteractorProvider =
        FilterInteractor_Factory.create(
            contentApiProvider,
            provideListCategoryProvider,
            provideBanedListProvider,
            provideConverterProvider);

    this.provideFilterProvider = DoubleCheck.provider((Provider) filterInteractorProvider);

    this.provideCacheOptionsProvider =
        DoubleCheck.provider(SingletonModule_ProvideCacheOptionsFactory.create());

    this.cacheMapProvider = CacheMap_Factory.create(provideCacheOptionsProvider);

    this.provideActCacheProvider = DoubleCheck.provider((Provider) cacheMapProvider);

    this.cacheMapProvider2 = CacheMap_Factory.create(provideCacheOptionsProvider);

    this.provideUserCacheProvider = DoubleCheck.provider((Provider) cacheMapProvider2);

    this.provideImageLoaderProvider =
        DoubleCheck.provider((Provider) LoadImageInteractor_Factory.create());

    this.provideLoginApiProvider =
        DoubleCheck.provider(
            RegLogModule_ProvideLoginApiFactory.create(
                builder.regLogModule, provideRetrofitProvider));

    this.appMembersInjector = App_MembersInjector.create(provideLoginApiProvider);

    this.loginPresenterMembersInjector =
        LoginPresenter_MembersInjector.create(
            provideLoginApiProvider, provideAccountPrefManagerProvider);

    this.provideAddressConverterProvider =
        DoubleCheck.provider((Provider) AddressConverter_Factory.create());

    this.improvementPlaceProvider = ImprovementPlace_Factory.create(contentApiProvider);

    this.improvementPlaceProvider2 = DoubleCheck.provider((Provider) improvementPlaceProvider);

    this.placeByLocProvider =
        PlaceByLocProvider_Factory.create(
            contentApiProvider, provideAddressConverterProvider, improvementPlaceProvider2);

    this.placeByLocProvider2 = DoubleCheck.provider((Provider) placeByLocProvider);

    this.placeByWordProvider =
        PlaceByWordProvider_Factory.create(
            contentApiProvider, provideAddressConverterProvider, improvementPlaceProvider2);

    this.placeByWordProvider2 = DoubleCheck.provider((Provider) placeByWordProvider);

    this.selectAddressPresenterMembersInjector =
        SelectAddressPresenter_MembersInjector.create(placeByLocProvider2, placeByWordProvider2);

    this.introPresenterMembersInjector =
        IntroPresenter_MembersInjector.create(provideAccountPrefManagerProvider);

    this.provideRegistrationApiProvider =
        DoubleCheck.provider(
            RegLogModule_ProvideRegistrationApiFactory.create(
                builder.regLogModule, provideRetrofitProvider));

    this.registrationPresenterMembersInjector =
        RegistrationPresenter_MembersInjector.create(
            provideRegistrationApiProvider, provideAccountPrefManagerProvider);

    this.cityProvider = CityProvider_Factory.create(contentApiProvider);

    this.cityProvider2 = DoubleCheck.provider((Provider) cityProvider);

    this.selectCityPresenterMembersInjector =
        SelectCityPresenter_MembersInjector.create(cityProvider2);

    this.fusedLocationClientProvider =
        DoubleCheck.provider(
            AppModule_FusedLocationClientFactory.create(
                builder.appModule, provideApplicationContextProvider));

    this.fusedLocationObserverProvider =
        FusedLocationObserver_Factory.create(
            fusedLocationClientProvider, providePermissionControllerProvider);

    this.provideLocationProvider = DoubleCheck.provider((Provider) fusedLocationObserverProvider);

    this.selectLocationPresenterMembersInjector =
        SelectLocationPresenter_MembersInjector.create(
            provideLocationProvider, providePermissionControllerProvider);

    this.actByLocationInteractorProvider =
        ActByLocationInteractor_Factory.create(contentApiProvider, provideConverterProvider);

    this.provideActByLocationProvider =
        DoubleCheck.provider((Provider) actByLocationInteractorProvider);

    this.lastLocationProvideProvider =
        LastLocationProvide_Factory.create(
            fusedLocationClientProvider, providePermissionControllerProvider);

    this.lastLocationProvideProvider2 =
        DoubleCheck.provider((Provider) lastLocationProvideProvider);

    this.mapPresenterMembersInjector =
        MapPresenter_MembersInjector.create(
            provideActByLocationProvider, lastLocationProvideProvider2);

    this.restorePresenterMembersInjector =
        RestorePresenter_MembersInjector.create(provideLoginApiProvider);

    this.accessRecoveryActivityMembersInjector =
        AccessRecoveryActivity_MembersInjector.create(provideLoginApiProvider);
  }

  @Override
  public Context context() {
    return provideApplicationContextProvider.get();
  }

  @Override
  public DataProvide<List<Category>> generalCategoryProvider() {
    return provideListCategoryProvider.get();
  }

  @Override
  public ContentApi contentApi() {
    return contentApiProvider.get();
  }

  @Override
  public ChatApi chatApi() {
    return provideChatApiProvider.get();
  }

  @Override
  public Retrofit retrofit() {
    return provideRetrofitProvider.get();
  }

  @Override
  public AsyncList<ActId> banedList() {
    return provideBanedListProvider.get();
  }

  @Override
  public Converter.Factory converterFactory() {
    return provideConverterFactoryProvider.get();
  }

  @Override
  public Gson gson() {
    return provideGsonProvider.get();
  }

  @Override
  public ContactsProvider provider() {
    return providerProvider.get();
  }

  @Override
  public AccountPreferenceManager accountPreferenceManager() {
    return provideAccountPrefManagerProvider.get();
  }

  @Override
  public PermissionController permissionController() {
    return providePermissionControllerProvider.get();
  }

  @Override
  public ErrorHandler errorHandler() {
    return new ErrorHandler(providerResourcesProvider.get());
  }

  @Override
  public Resources resources() {
    return providerResourcesProvider.get();
  }

  @Override
  public Interactor<String, Act> shappingDateInteractor() {
    return provideShappingDateInteractorProvider.get();
  }

  @Override
  public Interactor<Single<Category>, String> categoryById() {
    return provideCategoryByIdProvider.get();
  }

  @Override
  public MediaPlayer mediaPlayer() {
    return provideMediaPlayerProvider.get();
  }

  @Override
  public evfor.fun.skvader.convertors.Converter<Act, RsAct> actConverter() {
    return provideConverterProvider.get();
  }

  @Override
  public Interactor<Observable<Act>, FilterModel> filter() {
    return provideFilterProvider.get();
  }

  @Override
  public Cache<ActId, Act> actCache() {
    return provideActCacheProvider.get();
  }

  @Override
  public Cache<Integer, User> userCache() {
    return provideUserCacheProvider.get();
  }

  @Override
  public Interactor<Single<Bitmap>, String> imageLoader() {
    return provideImageLoaderProvider.get();
  }

  @Override
  public void inject(App application) {
    appMembersInjector.injectMembers(application);
  }

  @Override
  public void inject(LoginPresenter presenter) {
    loginPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(SelectAddressPresenter presenter) {
    selectAddressPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(IntroPresenter presenter) {
    introPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(RegistrationPresenter presenter) {
    registrationPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(SelectCityPresenter placesTask) {
    selectCityPresenterMembersInjector.injectMembers(placesTask);
  }

  @Override
  public void inject(FilterPresenter presenter) {
    MembersInjectors.<FilterPresenter>noOp().injectMembers(presenter);
  }

  @Override
  public void inject(SelectLocationPresenter presenter) {
    selectLocationPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(MapPresenter presenter) {
    mapPresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(RestorePresenter presenter) {
    restorePresenterMembersInjector.injectMembers(presenter);
  }

  @Override
  public void inject(AccessRecoveryActivity presenter) {
    accessRecoveryActivityMembersInjector.injectMembers(presenter);
  }

  public static final class Builder {
    private AppModule appModule;

    private RegLogModule regLogModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      if (regLogModule == null) {
        this.regLogModule = new RegLogModule();
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder regLogModule(RegLogModule regLogModule) {
      this.regLogModule = Preconditions.checkNotNull(regLogModule);
      return this;
    }
  }
}