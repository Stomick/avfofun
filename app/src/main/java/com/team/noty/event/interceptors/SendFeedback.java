package com.team.noty.event.interceptors;

import com.team.noty.event.models.FeedBack;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.ui.models.Review;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendFeedback implements Interactor<Single<RsBase>, FeedBack> {

    private MainApi api;

    @Inject
    SendFeedback(MainApi api) {
        this.api = api;
    }
    Review review;
    @Override
    public Single<RsBase> call(FeedBack feedBack) {
        return api.sendFeedBack(feedBack.eventId, feedBack.rating, feedBack.review);
    }
}
