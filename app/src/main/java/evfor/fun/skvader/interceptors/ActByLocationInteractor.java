package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.models.response.RsAct;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ActByLocationInteractor implements Interactor<Observable<Act>, Location> {

    private ContentApi api;
    private Converter<Act, RsAct> converter;

    @Inject
    ActByLocationInteractor(ContentApi api, Converter<Act, RsAct> converter) {
        this.api = api;
        this.converter = converter;
    }

    @Override
    public Observable<Act> call(Location location) {
        return api.filterByLocation(location.lat(), location.lon())
                .map(rsEventList -> rsEventList.answer)
                .flatMapObservable(Observable::fromIterable)
                .map(converter::convert);
    }
}
