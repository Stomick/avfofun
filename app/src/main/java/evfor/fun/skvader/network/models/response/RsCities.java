package evfor.fun.skvader.network.models.response;

import java.util.List;

public class RsCities extends RsBase {
    public List<City> cities;

    public class City {
        public int id;
        public String name;
        public double latitude, longitude;
    }
}
