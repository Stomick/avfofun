package evfor.fun.skvader.repository.data_provide;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.exceptions.NotFoundException;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.response.RsAddressFromLatLng;
import evfor.fun.skvader.network.models.response.RsPlaces;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PlaceByLocProvider implements DataProvide1<List<Address>, Location> {

    private ContentApi api;
    private Converter<Address, RsPlaces.Places> converter;
    private Interactor<Single<Address>, Address> improvement;

    @Inject
    PlaceByLocProvider(ContentApi api,
                              Converter<Address, RsPlaces.Places> converter,
                              Interactor<Single<Address>, Address> improvement) {
        this.api = api;
        this.converter = converter;
        this.improvement = improvement;
    }

    @Override
    public Single<List<Address>> provide(Location location) {
        return api.searchPlace(latlngToString(location), "ru")
                .flatMap(rsAddressFromLatLng -> {
                    if (rsAddressFromLatLng.results.isEmpty())
                        throw new NotFoundException("");
                    return Observable.just(rsAddressFromLatLng.results);
                })
                .flatMap(Observable::fromIterable)
                .map(this::toPlace)
                .map(converter::convert)
                .flatMapSingle(improvement::call)
                .toList();
    }

    private RsPlaces.Places toPlace(RsAddressFromLatLng.Address address) {
        return new RsPlaces.Places(address.place_id, address.vicinity, address.name);
    }

    private String latlngToString(Location latLng) {
        return String.valueOf(latLng.latitude) + "," + String.valueOf(latLng.longitude);
    }
}
