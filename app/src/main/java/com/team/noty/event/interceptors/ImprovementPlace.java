package com.team.noty.event.interceptors;

import com.team.noty.event.models.Address;
import com.team.noty.event.models.Location;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.models.response.RsPlaceDetails;
import com.team.noty.event.utils.LocationUtils;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class ImprovementPlace implements Interactor<Single<Address>, Address> {

    private ContentApi api;

    @Inject
    public ImprovementPlace(ContentApi api) {
        this.api = api;
    }

    @Override
    public Single<Address> call(Address address) {
        return api.getPlaceDetails(address.placeID, LocationUtils.getLocCode())
                .filter(rsPlaceDetails -> rsPlaceDetails.result!=null)
                .map(this::convert)
                .zipWith(Maybe.just(address), this::zip)
                .toSingle(address);
    }

    public Location convert(RsPlaceDetails placeDetails) {
        return new Location(placeDetails.result.geometry.location.lat, placeDetails.result.geometry.location.lng);
    }

    private Address zip(Location l, Address a){
        a.location = l;
        return a;
    }
}
