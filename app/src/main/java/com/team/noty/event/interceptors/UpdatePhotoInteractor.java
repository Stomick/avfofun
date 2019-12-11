package com.team.noty.event.interceptors;

import android.util.Log;

import com.team.noty.event.models.UpdatePhoto;
import com.team.noty.event.models.User;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.network.models.response.RsProfile;
import com.team.noty.event.repository.LocalRepository;
import com.team.noty.event.ui.fragments.main.CabinetTabFragment;
import com.team.noty.event.utils.RequestMapUtil;

import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class UpdatePhotoInteractor implements Interactor<Completable, UpdatePhoto> {
    private MainApi api;
    private LocalRepository<User, Integer> localRepository;

    @Inject
    public UpdatePhotoInteractor(MainApi api, LocalRepository<User, Integer> localRepository) {
        this.api = api;
        this.localRepository = localRepository;
    }

    @Override
    public Completable call(UpdatePhoto part) {
        if(part.delete())
            return api.deletePhoto(RequestMapUtil.create(UpdatePhoto.delete))
                    .toCompletable();
        CabinetTabFragment.photoPath = part.getPath();
        return api.editProfile(RequestMapUtil.uploadFile(part.getPath()))
                .toCompletable()
                .andThen(api.profile())
                .firstOrError()
                .flatMapCompletable(this::setAvatar);
    }

    private Completable setAvatar(RsProfile profile) {
        return Single.zip(Single.just(profile.account.userAvatar),
                localRepository.request(profile.account.user_id),
                (t1, t2) -> {
                    t2.imageUrl = t1;
                    return t2;
                })
                .flatMapCompletable(localRepository::edit);
    }
    public Disposable editPhoto(UpdatePhoto updatePhoto)
    {
        return api.editProfile(RequestMapUtil.uploadFile(updatePhoto.getPath()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
