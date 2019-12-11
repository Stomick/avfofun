package evfor.fun.skvader.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FilterCreator {

    public enum SORT {
        DATE, NAME, NAN
    }

    private static String currentCity = "";

    private static String POP = "pop";
    private static String CITY = "city";
    private static String CAT = "cat";
    private static String DATECREATE = "datecreate";
    private static String DATE = "date";
    private static String TIME = "time";
    private static String COUNT_P = "count_p";
    private static String WORD = "word";
    private static String PRICE = "price";
    private static String AGE = "age";
    private static String COM = "com";
    private static String LAT = "latitude";
    private static String LNG = "longitude";
    private static String SORT_BY = "sort";

    public static String getCurrentCity() {
        return currentCity;
    }

    public static SORT getSort(Map<String, String> filterMap) {
        if (filterMap != null && filterMap.containsKey(SORT_BY)) {
            SORT s = SORT.valueOf(filterMap.get(SORT_BY));
            filterMap.remove(SORT_BY);
            return s;
        } else return SORT.NAN;
    }

    public static Map<String, String> popular() {
        if (!currentCity.equals(""))
            return create()
                    .addCity(currentCity)
                    .setPopularSort(true)
                    .build();
        else return create()
                .setPopularSort(true)
                .build();
    }

    public static Map<String, String> withCategory(String categoryId) {
        return create()
                .addCategory(categoryId)
                .build();
    }

    public static Map<String, String> withCategoryCreate(String categoryId, String datecreate) {
        return create()
                .addCategory(categoryId)
                .addDateCreate(datecreate)
                .build();
    }

    public static Map<String, String> word(String word, Map<String, String> map) {
        return create()
                .addCity(currentCity)
                .addPrew(map)
                .addWord(word)
                .build();
    }

    public static Map<String, String> fromLocation(double lat, double lng) {
        return create().addLocation(lat, lng).build();
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private Map<String, String> map;

        private Builder() {
            map = new HashMap<>();
        }

        public Builder addPrew(Map<String, String> prewMap) {
            if (prewMap != null)
                map.putAll(prewMap);
            return this;
        }

        public Builder addLocation(double lat, double lng) {
            map.put(LAT, String.valueOf(lat));
            map.put(LNG, String.valueOf(lng));
            return this;
        }

        public Builder addCity(String city) {
            map.put(CITY, city);
            return this;
        }

        public Builder setPopularSort(boolean up) {
            map.put(POP, up ? "up" : "down");
            return this;
        }

        public Builder addCategory(String categoryId) {
            map.put(CAT, categoryId);
            return this;
        }

        public Builder addDateCreate(String datecreate) {
            map.put(DATECREATE, datecreate);
            return this;
        }

        public Builder addDate(String date) {
            map.put(DATE, DateFormatter.reformating(date));
            return this;
        }

        public Builder addTime(String time) {
            map.put(TIME, time);
            return this;
        }

        public Builder addCount(int count) {
            map.put(COUNT_P, String.valueOf(count));
            return this;
        }

        public Builder addWord(String word) {
            map.put(WORD, word);
            return this;
        }

        public Builder addPrice(String word) {
            map.put(PRICE, word);
            return this;
        }

        public Builder addAge(int age) {
            map.put(AGE, String.valueOf(age));
            return this;
        }

        public Builder setOnlyComm(boolean onlycomm) {
            map.put(COM, onlycomm ? "on" : "");
            return this;
        }

        public Builder setSort(SORT sort) {
            map.put(SORT_BY, sort.name());
            return this;
        }

        public Map<String, String> build() {
            if (!map.containsKey(CITY))
                map.put(CITY, currentCity);
            Collection<String> values = map.values();
            while (values.remove(null)) {
            }
            while (values.remove("")) {
            }
            return map;
        }
    }

    public static void setCurrCity(String city) {
        if (city != null)
            currentCity = city;
    }
}
