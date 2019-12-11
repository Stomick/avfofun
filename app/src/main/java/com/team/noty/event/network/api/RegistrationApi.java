package com.team.noty.event.network.api;

import com.team.noty.event.network.URLS;
import com.team.noty.event.network.models.request.RqRegistration;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsRegistration;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegistrationApi {

    @POST(URLS.Registration)
    Observable<RsRegistration> registration(@Body RqRegistration registration);

    @POST(URLS.RegWithSoc)
    @FormUrlEncoded
    Single<RsBase> regWithSocKey(@Header("token") String tokenUser, @Field("source_id") String socId, @Field("source") String VKorFB);

    @POST(URLS.SetSoc)
    @FormUrlEncoded
    Single<RsBase> setSoc(@Header("token") String tokenUser, @Field("source") String soc, @Field("id") String id);
}
