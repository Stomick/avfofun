package com.team.noty.event.network.models.request;

public class RqCity {
    public String city;
    public Double lat, lon;

    public RqCity(String city) {
        this.city = city;
    }

    public RqCity() {
    }

    public RqCity(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
