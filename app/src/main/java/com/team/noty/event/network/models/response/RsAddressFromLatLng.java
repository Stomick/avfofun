package com.team.noty.event.network.models.response;

import java.util.List;

public class RsAddressFromLatLng {
    public String status;
    public List<Address> results;

    public class Address {
        public String name;
        public String vicinity;
        public String place_id;
        public String icon;
    }
}
