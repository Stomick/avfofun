package evfor.fun.skvader.interceptors;

import evfor.fun.skvader.models.UpdatePhoto;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.network.api.MainApi;
import evfor.fun.skvader.network.models.response.RsProfile;
import evfor.fun.skvader.repository.LocalRepository;
import evfor.fun.skvader.ui.fragments.main.CabinetTabFragment;
import evfor.fun.skvader.utils.RequestMapUtil;

import javax.inject.Inject;

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
        return Single.zip(Single.just(profile.answer.userAvatar),
                localRepository.request(profile.answer.user_id),
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
