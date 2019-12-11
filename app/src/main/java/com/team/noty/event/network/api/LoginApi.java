package com.team.noty.event.network.api;

import com.team.noty.event.network.URLS;
import com.team.noty.event.network.models.request.RqFBToken;
import com.team.noty.event.network.models.request.RqLogin;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsLogin;


import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginApi {

    @POST(URLS.LoginWithSoc)
    @FormUrlEncoded
    Observable<RsLogin> login(@Field("source_id") String id, @Field("source") String source);

    @POST(URLS.Login)
    Observable<RsLogin> login(@Body RqLogin login);

    @POST(URLS.SetFBToken)
    Single<RsBase> sendFBToken(@Header("token") String tokenUser, @Body RqFBToken token);

    @POST(URLS.Restore)
    @FormUrlEncoded
    Observable<RsBase> restore(@Header("apikey") String apikey, @Field("email") String email);

    @POST(URLS.Restore)
    @FormUrlEncoded
    Call<RsBase> restores(@Header("apikey") String apikey, @Field("email") String email);
}
