package evfor.fun.skvader.convertors;

import evfor.fun.skvader.network.models.response.RsPlaces;
import evfor.fun.skvader.models.Address;

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
