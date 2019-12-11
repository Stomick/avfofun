package com.team.noty.event.convertors;

import com.team.noty.event.network.models.response.RsPlaces;
import com.team.noty.event.models.Address;

import javax.inject.Inject;

public class AddressConverter implements Converter<Address, RsPlaces.Places> {

    @Inject
    public AddressConverter() {
    }

    @Override
    public Address convert(RsPlaces.Places places) {
        Address address = new Address();
        address.name = places.title;
        address.title = places.description;
        address.placeID = places.id;
        return address;
    }
}
