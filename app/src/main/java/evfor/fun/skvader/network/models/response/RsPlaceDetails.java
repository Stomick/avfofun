package evfor.fun.skvader.network.models.response;

public class RsPlaceDetails {

    public Place result;

    public class Place {
    public String formatted_address;
    public Geometry geometry;

    public class Geometry {
        public Location location;

        public class Location {
            public double lat, lng;
        }
    }
}}
