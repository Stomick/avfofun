package com.team.noty.event.network.api;

import com.team.noty.event.network.URLS;
import com.team.noty.event.network.models.response.RsAdminUserList;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsCount;
import com.team.noty.event.network.models.response.RsGetComm;
import com.team.noty.event.network.models.response.RsGetEvent;
import com.team.noty.event.network.models.response.RsNotification;
import com.team.noty.event.network.models.response.RsProfile;
import com.team.noty.event.network.models.response.RsSoc;
import com.team.noty.event.ui.models.Review;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @GET(URLS.Profile)
    Observable<RsProfile> profile();

    @GET(URLS.GetCountnoty)
    Single<RsCount> getCountNoty();

    @GET(URLS.Profile)
    Observable<RsProfile> profile(@Query("id") String id);

    @Multipart
    @POST(URLS.Create)
    @Deprecated
    Single<RsBase> create(@Path("x") String path,
                              @PartMap Map<String, RequestBody> body);

    @Multipart
    @POST(URLS.Create)
    @Deprecated
    Single<RsBase> create(@Path("x") String path,
                              @PartMap Map<String, RequestBody> body,
                              @Part MultipartBody.Part image);

    @Multipart
    @POST(URLS.Create)
    @Deprecated
    Single<RsBase> write(@Path("x") String path, @Query("id") String id,
                         @PartMap Map<String, RequestBody> body);

    @Multipart
    @POST(URLS.Create)
    Single<RsBase> write(@Path("x") String path, @Query("id") String id,
                         @PartMap Map<String, RequestBody> body,
                         @Part MultipartBody.Part image);

    @Multipart
    @POST(URLS.EditProfile)
    Single<RsBase> editProfile(@PartMap Map<String, RequestBody> body);

    @Multipart
    @POST(URLS.EditProfile)
    Single<RsBase> editAbout(@Part("userInfo") RequestBody about);

    @Multipart
    @POST(URLS.EditProfile)
    Single<RsBase> deletePhoto(@Part("userAvatar") RequestBody body);

    @Multipart
    @POST(URLS.EditProfile)
    Single<RsBase> editProfile(@Part MultipartBody.Part image);

    @Multipart
    @POST(URLS.EditProfile)
    Observable<RsBase> editCategories(@Part("userCategories") RequestBody categories);

    @Multipart
    @POST(URLS.EditProfile)
    Single<RsBase> editProfile(@PartMap Map<String, RequestBody> body,
                                   @Part MultipartBody.Part image);

    @GET(URLS.EventDetails)
    Observable<RsGetEvent> event(@Query("id") String id);

    @GET(URLS.CommunityDetails)
    Observable<RsGetComm> community(@Query("id") String id);

    @POST(URLS.maineditprofile)
    @Multipart
    Single<RsBase> mainEditProfile(@PartMap Map<String, RequestBody> body);

    @POST(URLS.EnterEvent)
    @FormUrlEncoded
    Observable<RsBase> enterEvent(@Field("comm") String comm,
                                  @Field("id") String id);

    @POST(URLS.EnterCommunity)
    @FormUrlEncoded
    Observable<RsBase> enterComm(@Field("comm") String comm,
                                 @Field("id") String id);

    @GET(URLS.GetNotification)
    Single<RsNotification> getNotifications();

    @POST(URLS.SetNotification)
    @FormUrlEncoded
    Single<RsBase> sendComplaint(@Field("object_type") String type,
                                 @Field("object_id") String object_id ,
                                 @Field("recipient_id") String recipient_id ,
                                 @Field("notification_id") Integer notification_id);

    @POST(URLS.SetNotification)
    @FormUrlEncoded
    Single<RsBase> sendComplaint(@Field("object_type") String type,
                                 @Field("object_id") String object_id ,
                                 @Field("recipient_id") String recipient_id ,
                                 @Field("notification_id") Integer notification_id,
                                 @Field("cause_id") Integer cause_id);

    @POST(URLS.SetNotification)
    @FormUrlEncoded
    Single<RsBase> sendComplaint(@Field("object_type") String type,
                                 @Field("object_id") String object_id,
                                 @Field("recipient_id") String recipient_id,
                                 @Field("notification_id") Integer notification_id,
                                 @Field("cause_id") Integer cause_id,
                                 @Field("message") String message);

    @POST(URLS.DeleteEvent)
    @FormUrlEncoded
    Single<RsBase> deleteEvent(@Field("object_type") String type,
                                 @Field("object_id") String object_id ,
                                 @Field("cause_id") Integer cause_id,
                                 @Field("cause_text") String cause_text);
    @POST(URLS.SetUserStatus)
    @FormUrlEncoded
    Single<RsBase> acceptRemoveUser(@Field("object_type") String objType,
                                    @Field("object_id") String objId,
                                    @Field("user_id") String userId,
                                    @Field("status") String status);
    @POST(URLS.GetAdminUserList)
    @FormUrlEncoded
    Single<RsAdminUserList> getAdminUsers(@Field("object_type") String type, @Field("object_id") String id);

    @POST(URLS.SetSoc)
    @FormUrlEncoded
    Single<RsBase> setSoc(@Field("source") String soc, @Field("id") String id);

    @GET(URLS.GetSoc)
    Single<RsSoc> getSoc();

    @GET(URLS.GetSoc)
    Single<RsSoc> getSoc(@Query("id") String id);

    @POST(URLS.SetReview)
    @FormUrlEncoded
    Single<RsBase> sendFeedBack(@Field("event_id") String id, @Field("rating") String rating, @Field("review") String review);

    @GET(URLS.GetReview)
    Single<Review> getReview(@Query("event_id")String event_id);
}
