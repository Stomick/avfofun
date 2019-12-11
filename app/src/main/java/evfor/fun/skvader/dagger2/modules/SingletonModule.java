package evfor.fun.skvader.dagger2.modules;

import android.graphics.Bitmap;

import evfor.fun.skvader.convertors.ActConverter;
import evfor.fun.skvader.convertors.AddressConverter;
import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.interceptors.ActByLocationInteractor;
import evfor.fun.skvader.interceptors.AsyncList;
import evfor.fun.skvader.interceptors.BanedActList;
import evfor.fun.skvader.interceptors.CategoryById;
import evfor.fun.skvader.interceptors.ImprovementPlace;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.network.models.response.RsPlaces;
import evfor.fun.skvader.repository.cache.Cache;
import evfor.fun.skvader.repository.cache.CacheMap;
import evfor.fun.skvader.repository.cache.CacheOptions;
import evfor.fun.skvader.repository.data_observe.FusedLocationObserver;
import evfor.fun.skvader.repository.data_provide.CategoryListProvide;
import evfor.fun.skvader.interceptors.CityProvider;
import evfor.fun.skvader.repository.data_observe.DataObserver;
import evfor.fun.skvader.interceptors.FilterInteractor;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.interceptors.LoadImageInteractor;
import evfor.fun.skvader.interceptors.ShapingDateInteractor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.repository.data_provide.DataProvide1;
import evfor.fun.skvader.repository.data_provide.LastLocationProvide;
import evfor.fun.skvader.repository.data_provide.PlaceByLocProvider;
import evfor.fun.skvader.repository.data_provide.PlaceByWordProvider;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Single;

@Module
public interface SingletonModule {

    @Singleton
    @Binds
    Interactor<Single<Category>, String> provideCategoryById(CategoryById categoryById);

    @Singleton
    @Binds
    Interactor<String, Act> provideShappingDateInteractor(ShapingDateInteractor shapingDateInteractor);

    @Singleton
    @Binds
    DataProvide<List<Category>> provideListCategory(CategoryListProvide provider);

    @Singleton
    @Binds
    DataObserver<Location> provideLocation(FusedLocationObserver provider);

    @Singleton
    @Binds
    Interactor<Observable<Act>, Location> provideActByLocation(ActByLocationInteractor interactor);

    @Singleton
    @Binds
    Converter<Act, RsAct> provideConverter(ActConverter converter);

    @Singleton
    @Binds
    Interactor<Observable<City>, RqCity> cityProvider(CityProvider provider);

    @Singleton
    @Binds
    Interactor<Observable<Act>, FilterModel> provideFilter(FilterInteractor filterInteractor);

    @Singleton
    @Provides
    static CacheOptions provideCacheOptions() {
        return new CacheOptions(10);
    }

    @Singleton
    @Binds
    Cache<ActId, Act> provideActCache(CacheMap<ActId, Act> cacheMap);

    @Singleton
    @Binds
    Cache<Integer, User> provideUserCache(CacheMap<Integer, User> cacheMap);

    @Singleton
    @Binds
    Interactor<Single<Bitmap>, String> provideImageLoader(LoadImageInteractor imageInteractor);

    @Binds
    @Singleton
    AsyncList<ActId> provideBanedList(BanedActList actList);


    @Binds
    @Singleton
    DataProvide1<List<Address>, Location> placeByLoc(PlaceByLocProvider provider);

    @Binds
    @Singleton
    DataProvide1<List<Address>, String> placeByWord(PlaceByWordProvider provider);

    @Binds
    @Singleton
    Interactor<Single<Address>, Address> improvementPlace(ImprovementPlace improvementPlace);

    @Binds
    @Singleton
    Converter<Address, RsPlaces.Places> provideAddressConverter(AddressConverter addressConverter);

    @Binds
    @Singleton
    DataProvide<Location> lastLocationProvide(LastLocationProvide provide);
}
