package com.team.noty.event.network.api;

import com.team.noty.event.network.URLS;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsMessage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ChatApi {

    @GET(URLS.EventMessages)
    Observable<List<RsMessage>> getEventMessages(@Path("eventId") String eventId,
                                                 @Query("type") String type);

    @GET(URLS.PrivateMessages)
    Observable<List<RsMessage>> getUserMessages(@Path("eventId") String joinEventId,
                                                @Path("userId") String userId,
                                                @Query("type") String type);

    @POST("read-messages")
    Observable<RsBase> sendMessageInfo(@Query("userId") String userId,
                                         @Query("messages") List<RsMessage> type);
    @FormUrlEncoded
    @POST("read-messages")
    Observable<RsBase> sendMessageRead(@Field("userId") String userId,
                                       @Field("messages") List<String> type);
}
