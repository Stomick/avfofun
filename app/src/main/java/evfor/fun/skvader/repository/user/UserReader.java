package evfor.fun.skvader.repository.user;

import android.support.annotation.NonNull;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsProfile;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.cache.Cache;
import evfor.fun.skvader.utils.StringUtils;
import evfor.fun.skvader.utils.UrlUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserReader implements ReaderRepos<User, Integer> {

    private MainApi api;
    private LocalRepository<User, Integer> localRepository;
    private Cache<Integer, User> cache;

    @Inject
    public UserReader(MainApi api,
                      LocalRepository<User, Integer> localRepository,
                      Cache<Integer, User> cache) {
        this.api = api;
        this.cache = cache;
        this.localRepository = localRepository;
    }

    @Override
    @NonNull
    public Single<User> request(@NonNull Integer id) {
        return Single.just(id)
                .subscribeOn(Schedulers.io())
                .map(cache::get)
                .onErrorResumeNext(netWork(id));
    }

    private Single<User> netWork(Integer id) {
        return request(String.valueOf(id))
                .map(this::fromApi)
                .doOnNext(this::caching)
                .singleOrError();
    }

    private void caching(User user) {
        cache.put(StringUtils.toIntOr0(user.id()), user);
    }

    private Observable<RsProfile> request(String id) {
        if(AuthData.equalId(id)||id.equals("0"))
            return api.profile();
        else
            return api.profile(id);
        }

    private User fromApi(RsProfile profile) {
        RsProfile.Account account = profile.answer;
        User user = new User();
        if(account != null) {
            user.categories = account.userCategories == null ? new ArrayList<>() : account.userCategories;
            user.events = account.events;
            user.communities = account.communities;
            user.reviews = account.reviews;
            user.rating = account.userRating;
            user.level = account.typeRank;
            user.gender = account.gender;
            user.date = account.birthday;
            user.email = account.email;
            user.created = account.makedCommunities + account.makedEvents;
            user.visited = account.makedCommunities + account.makedEvents;
            user.email = account.email;
            user.reg = account.dateReg;
            user.phone = account.userPhone;
            user.firstname = account.username;
            user.lastname = account.surename;
            user.id = account.user_id;
            user.city = account.userCity;
            user.info = account.userInfo;
            user.sendMail = account.notificationEmail != 0 ? 1 : 0;
            user.commAccess = account.AccessCommunity != 0 ? 1 : 0;
            user.eventAccess = account.AccessEvent != 0 ? 1 : 0;
            user.imageUrl = UrlUtil.correct(account.userAvatar);

            user.vk_id = account.vk;
            user.fb_id = account.fb;
            user.tw_id = account.tw;
            user.inst_id = account.inst;
        }
        return user;
    }

}
