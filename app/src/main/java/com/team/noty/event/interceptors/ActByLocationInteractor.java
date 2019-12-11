package com.team.noty.event.interceptors;

import com.team.noty.event.convertors.Converter;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Location;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsAct;

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
