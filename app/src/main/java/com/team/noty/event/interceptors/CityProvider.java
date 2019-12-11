package com.team.noty.event.interceptors;

import com.team.noty.event.models.Location;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.request.RqCity;
import com.team.noty.event.network.models.response.RsCities;
import com.team.noty.event.models.City;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CityProvider implements Interactor<Observable<City>, RqCity> {

    private ContentApi api;

    @Inject
    CityProvider(ContentApi api) {
        this.api = api;
    }

    @Override
    public Observable<City> call(RqCity rqCity) {
        return api.getCities(rqCity, ContentApi.APIKEY)
                .map(rsCities -> rsCities.cities)
                .flatMapObservable(Observable::fromIterable)
                .map(this::convert);
    }

    private City convert(RsCities.City rsCity) {
        City uiCity = new City();
        uiCity.name = rsCity.name;
        uiCity.location = new Location(rsCity.latitude, rsCity.longitude);
        return uiCity;
    }
}
