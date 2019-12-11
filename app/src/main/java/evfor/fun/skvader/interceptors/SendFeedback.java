package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.FeedBack;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsBase;
import evfor.fun.skvader.ui.models.Review;

import javax.inject.Inject;

import io.reactivex.Single;

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
