package evfor.fun.skvader.network.api;

import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.network.models.response.RsAddressFromLatLng;
import evfor.fun.skvader.network.models.response.RsAddressFromWord;
import evfor.fun.skvader.network.models.response.RsCategory;
import evfor.fun.skvader.network.models.response.RsCities;
import evfor.fun.skvader.network.models.response.RsEventList;
import evfor.fun.skvader.network.models.response.RsPlaceDetails;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ContentApi {
    String APIKEY = "qmhfN]P)YM7G^9UaX3m4ccDm4N7Y6R9zL6KnN3Bnk@V1";

    @GET(URLS.CategoryList)
    Observable<RsCategory> category();

    @GET(URLS.Event)
    Observable<RsEventList> searchList(@Query("all") int all);

    @POST(URLS.Cities)
    Single<RsCities> getCities(@Body RqCity rqCity, @Header("apikey") String apikey);

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?rankby=distance&key=AIzaSyDuTrsL2GU_53et8BCXUR3YqL0Tc_Iyysw")
    Observable<RsAddressFromLatLng> searchPlace(@Query("location") String latlng, @Query("language") String lngcode);

    @GET("https://maps.googleapis.com/maps/api/place/details/json?key=AIzaSyDuTrsL2GU_53et8BCXUR3YqL0Tc_Iyysw")
    Single<RsPlaceDetails> getPlaceDetails(@Query("placeid") String placeId, @Query("language") String lngcode);

    @GET("https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyDuTrsL2GU_53et8BCXUR3YqL0Tc_Iyysw&types=address")
    Observable<RsAddressFromWord> searchPlaceByWord(@Query("input") String keyword, @Query("language") String lngcode);

    @GET(URLS.Filter)
    Observable<RsEventList> filter(@QueryMap Map<String, String> queries);

    @GET(URLS.Filter)
    Single<RsEventList> filterByLocation(@Query("latitude") String lat, @Query("longitude") String lon);
}
