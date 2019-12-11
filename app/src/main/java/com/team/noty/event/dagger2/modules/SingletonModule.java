package com.team.noty.event.dagger2.modules;

import android.graphics.Bitmap;

import com.team.noty.event.convertors.ActConverter;
import com.team.noty.event.convertors.AddressConverter;
import com.team.noty.event.convertors.Converter;
import com.team.noty.event.interceptors.ActByLocationInteractor;
import com.team.noty.event.interceptors.AsyncList;
import com.team.noty.event.interceptors.BanedActList;
import com.team.noty.event.interceptors.CategoryById;
import com.team.noty.event.interceptors.ImprovementPlace;
import com.team.noty.event.models.Address;
import com.team.noty.event.network.models.response.RsPlaces;
import com.team.noty.event.repository.cache.Cache;
import com.team.noty.event.repository.cache.CacheMap;
import com.team.noty.event.repository.cache.CacheOptions;
import com.team.noty.event.repository.data_observe.FusedLocationObserver;
import com.team.noty.event.repository.data_provide.CategoryListProvide;
import com.team.noty.event.interceptors.CityProvider;
import com.team.noty.event.repository.data_observe.DataObserver;
import com.team.noty.event.interceptors.FilterInteractor;
import com.team.noty.event.repository.data_provide.DataProvide;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.interceptors.LoadImageInteractor;
import com.team.noty.event.interceptors.ShapingDateInteractor;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Category;
import com.team.noty.event.models.FilterModel;
import com.team.noty.event.models.Location;
import com.team.noty.event.models.User;
import com.team.noty.event.network.models.request.RqCity;
import com.team.noty.event.network.models.response.RsAct;
import com.team.noty.event.models.City;
import com.team.noty.event.repository.data_provide.DataProvide1;
import com.team.noty.event.repository.data_provide.LastLocationProvide;
import com.team.noty.event.repository.data_provide.PlaceByLocProvider;
import com.team.noty.event.repository.data_provide.PlaceByWordProvider;

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
