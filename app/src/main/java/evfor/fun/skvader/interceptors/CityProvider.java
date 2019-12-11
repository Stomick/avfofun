package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.network.models.response.RsCities;
import evfor.fun.skvader.models.City;

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
