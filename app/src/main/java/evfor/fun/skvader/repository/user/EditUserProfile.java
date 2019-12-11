package evfor.fun.skvader.repository.user;

import android.support.annotation.NonNull;

import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.utils.DateFormatter;
import evfor.fun.skvader.utils.RequestMapUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import okhttp3.RequestBody;

import static evfor.fun.skvader.utils.ObjUtils.nullEquals;
import static evfor.fun.skvader.utils.RequestMapUtil.input;

public class EditUserProfile implements WriterRepos<User> {

    private MainApi api;
    private LocalRepository<User, Integer> localRepository;
    private ReaderRepos<User, Integer> userReader;

    @Inject
    EditUserProfile(MainApi api, LocalRepository<User, Integer> localRepository, ReaderRepos<User, Integer> userReader) {
        this.api = api;
        this.localRepository = localRepository;
        this.userReader = userReader;
    }

    @Override
    public Completable edit(@NonNull User new_) {
//        if (AuthData.notEqualId(new_))
//            localRepository.edit(new_);
//        else
            return userReader.request(new_.id)
                    .observeOn(AndroidSchedulers.mainThread())
                    //.subscribeOn(AndroidSchedulers.mainThread())
                    .zipWith(Single.just(new_), this::nullFieldIfEquals)
                    .flatMapCompletable(user -> Completable.mergeArrayDelayError(request(user), requestMain(user)))
                    .andThen(update(new_));
    }

    private Completable update(User user) {
        return localRepository.edit(user);
    }

    private Completable request(User user) {
        Map<String, RequestBody> map = params(user);
        if (!map.isEmpty())
            return api.editProfile(map, RequestMapUtil.uploadFile(user.imageUrl)).observeOn(AndroidSchedulers.mainThread()).toCompletable();
        else
            return Completable.complete();
    }

    private Completable requestMain(User user) {
        Map<String, RequestBody> map = mainParams(user);
        if (!map.isEmpty())
            return api.mainEditProfile(map).observeOn(AndroidSchedulers.mainThread()).toCompletable();
        else
            return Completable.complete();
    }

    private Map<String, RequestBody> params(User user) {
        Map<String, RequestBody> map = new HashMap<>();
        input(map, "userCategories", user.categories);
        input(map, "userInfo", user.info);
        input(map, "userPhone", user.phone);
        input(map, "userCity", user.city);
        input(map, "notificationEmail", user.sendMail);
        input(map, "AccessEvent", user.eventAccess);
        input(map, "AccessCommunity", user.commAccess);
        return map;
    }

    private Map<String, RequestBody> mainParams(User user) {
        Map<String, RequestBody> map = new HashMap<>();
        input(map, "gender", user.gender);
        input(map, "firstname", user.firstname);
        input(map, "surename", user.lastname);
        input(map, "bdate", user.date);
        input(map, "email", user.email);
        return map;
    }

    private User nullFieldIfEquals(User old, User new_) {
        User editable = new User();
        editable.gender = nullEquals(old.gender, new_.gender);
        if (new_.firstname!=null)
            editable.firstname = new_.firstname;
        if(new_.lastname!=null)
            editable.lastname = new_.lastname;
        editable.date = DateFormatter.reformating(nullEquals(old.date, new_.date));
        editable.email = nullEquals(old.email, new_.email);
        if(new_.city!=null)
            editable.city = new_.city;
        editable.phone = nullEquals(old.phone, new_.phone);
        editable.sendMail = new_.sendMail;
        editable.eventAccess = new_.eventAccess;

        editable.commAccess = new_.commAccess;
        if (new_.categories!=null)
            editable.categories = new_.categories;
        if (new_.info!=null)
            editable.info = new_.info;
        return editable;
    }
}
