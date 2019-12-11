package evfor.fun.skvader.network.models.response;

import java.util.List;

public class RsAddressFromWord {
    public List<Place> predictions;

    public class Place{
        public String description;
        public String place_id;
        public Struct structured_formatting;
        public class Struct{
            public String main_text;
            public String secondary_text;
        }
    }
}
