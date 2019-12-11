package evfor.fun.skvader.network.models.response;

import java.util.ArrayList;
import java.util.List;

public class RsPlaces {
    public List<Places> placesList;

    public void add(String id, String description, String title){
        if(placesList==null)
            placesList = new ArrayList<>();
        placesList.add(new Places(id, description, title));
    }

    public static class Places {
        public String description;
        public String title;
        public String id;

        public Places(String id, String description, String title) {
            this.description = description;
            this.id = id;
            this.title = title;
        }
    }
}
