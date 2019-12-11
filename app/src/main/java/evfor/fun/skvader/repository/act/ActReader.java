package evfor.fun.skvader.repository.act;

import android.support.annotation.NonNull;

import evfor.fun.skvader.convertors.Converter;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsAct;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.cache.Cache;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ActReader implements ReaderRepos<Act, ActId> {

    private MainApi api;
    private Interactor<Single<Category>, String> categoryById;
    private Converter<Act, RsAct> converter;
    private Cache<ActId, Act> cache;

    @Inject
    ActReader(MainApi api,
              Interactor<Single<Category>, String> categoryById,
              Converter<Act, RsAct> converter,
              Cache<ActId, Act> cache) {
        this.api = api;
        this.cache = cache;
        this.categoryById = categoryById;
        this.converter = converter;
    }

    private Observable<RsAct> requestSelect(ActId actId) {
        return actId.isEvent
                ? api.event(actId.id).map(rsGetEvent -> rsGetEvent.answer)
                : api.community(actId.id).map(rsGetComm -> rsGetComm.community);
    }

    @Override
    @NonNull
    public Single<Act> request(@NonNull ActId actId) {
        return Single.just(actId)
                .subscribeOn(Schedulers.io())
                .map(cache::get)
                .onErrorResumeNext(netWork(actId));
    }

    private Single<Act> netWork(ActId actId) {
        return requestSelect(actId)
                .map(converter::convert)
                .singleOrError()
                .onErrorResumeNext(Single.never())
                .flatMap(this::loadLogo)
                .doOnSuccess(this::caching);
    }

    private void caching(Act act) {
        cache.put(new ActId(act.id(), act.isEvent()), act);
    }

    private Single<Act> loadLogo(Act act) {
        return categoryById.call(act.category_id)
                .zipWith(Single.just(act), this::setLogo)
                .onErrorReturnItem(act);
    }

    private Act setLogo(Category category, Act act) {
        if (act.imageUrl == null)
            act.imageUrl = category.logoUrl;
        return act;
    }

}
