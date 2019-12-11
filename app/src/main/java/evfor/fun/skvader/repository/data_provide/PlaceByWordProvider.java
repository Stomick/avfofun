package evfor.fun.skvader.repository.data_provide;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.exceptions.NotFoundException;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.response.RsAddressFromWord;
import evfor.fun.skvader.network.models.response.RsPlaces;
import evfor.fun.skvader.utils.LocationUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PlaceByWordProvider implements DataProvide1<List<Address>, String> {

    private ContentApi api;
    private Converter<Address, RsPlaces.Places> converter;
    private Interactor<Single<Address>, Address> improvement;

    @Inject
    PlaceByWordProvider(ContentApi api,
                               Converter<Address, RsPlaces.Places> converter,
                               Interactor<Single<Address>, Address> improvement) {
        this.api = api;
        this.converter = converter;
        this.improvement = improvement;
    }

    @Override
    public Single<List<Address>> provide(String word) {
        return api.searchPlaceByWord(word, LocationUtils.getLocCode())
                .flatMap(rsAddressFromLatLng -> {
                    if (rsAddressFromLatLng.predictions.isEmpty())
                        throw new NotFoundException("");
                    return Observable.just(rsAddressFromLatLng.predictions);
                })
                .flatMap(Observable::fromIterable)
                .map(this::toPlace)
                .map(converter::convert)
                .flatMapSingle(improvement::call)
                .toList();
    }

    private RsPlaces.Places toPlace(RsAddressFromWord.Place place) {
        return new RsPlaces.Places(place.place_id, place.description, place.structured_formatting.main_text);
    }
}
