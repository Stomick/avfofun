package com.team.noty.event.utils;

import com.team.noty.event.exceptions.NoJointActsException;
import com.team.noty.event.models.ActId;
import com.team.noty.event.network.api.ContentApi;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsBase;
import com.team.noty.event.network.models.response.RsAct;
import com.team.noty.event.network.models.response.RsNotification;
import com.team.noty.event.network.models.response.RsProfile;
import com.team.noty.event.ui.models.UiComment;
import com.team.noty.event.ui.models.UiUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ContentLoader {

    public static Observable<RsAct> setImageUrl(RsAct event, ContentApi api) {
        if (event.logo == null)
            return Observable.zip(Observable.just(event), getCategoryImage(event.category_id, api),
                    (event1, s) -> {
                        event1.logo = s;
                        return event1;
                    });
        else return Observable.just(event);
    }

    private static Observable<String> getCategoryImage(String id, ContentApi api) {
        return api.category()
                .map(rsCategory -> rsCategory.answer)
                .flatMap(Observable::fromIterable)
                .filter(category -> category.category_id.equals(String.valueOf(id)))
                .map(category -> category.urlimg);
    }

    public static Observable<UiComment> setUser(UiComment comment, Observable<RsProfile> observable) {
        return Observable.zip(Observable.just(comment), observable.map(rsProfile -> rsProfile.account), (comment1, account) -> {
            comment1.userName = account.username;
            comment1.userPhotoUrl = account.userAvatar;
            return comment1;
        });
    }

    public static Observable<UiUser> loadUiUser(Observable<RsProfile> observable) {
        return observable
                .map(rsProfile -> rsProfile.account)
                .map(Converter::toUi);
    }

    public static Observable<UiUser> loadUser(MainApi api, String id) {
        return api.profile(id)
                .map(rsProfile -> rsProfile.account)
                .map(Converter::toDB)
                .map(Converter::toUi);
    }


    public static Single<ActId> joingEventComm(MainApi api, String userId0, String userId1) {
        Single<List<ActId>> list0 = loadUserEventCommIds(api, userId0);
        Single<List<ActId>> list1 = loadUserEventCommIds(api, userId1);
        return list0.zipWith(list1, ContentLoader::joinId);
    }

    public static ActId joinId(List<ActId> oneUser, List<ActId> twoUser) throws NoJointActsException {
        ActId join = null;
        for (ActId id : oneUser)
            for (ActId id1 : twoUser)
                if (id.isEvent == id1.isEvent)
                    if (id.id.equals(id1.id)) {
                        join = id;
                        break;
                    }
        if (join == null)
            throw new NoJointActsException();
        return join;
    }

    private static Single<List<ActId>> loadUserEventCommIds(MainApi api, String userId) {
        return api.profile(userId)
                .singleOrError()
                .map(profile -> mergeIds(profile.account.communities, profile.account.events));
    }

    private static List<ActId> mergeIds(List<String> comm, List<String> event) {
        if (comm == null)
            comm = new ArrayList<>();
        if (event == null)
            event = new ArrayList<>();
        List<ActId> list = createEventCommIdList(comm, false);
        list.addAll(createEventCommIdList(event, true));
        return list;
    }

    private static List<ActId> createEventCommIdList(List<String> ids, boolean isEvent) {
        List<ActId> actIds = new ArrayList<>();
        for (String id : ids)
            actIds.add(new ActId(id, isEvent));
        return actIds;
    }
}
