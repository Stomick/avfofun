package evfor.fun.skvader.models;

import java.io.Serializable;

public class Location implements Serializable{
    public double latitude, longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String lat() {
        return String.valueOf(latitude);
    }

    public String lon() {
        return String.valueOf(longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location)
            return latitude == ((Location) obj).latitude && longitude == ((Location) obj).longitude;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(latitude) + ":" + String.valueOf(longitude) + ")";
    }
}
