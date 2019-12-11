package com.team.noty.event.repository.user;

import com.team.noty.event.models.User;
import com.team.noty.event.repository.ReaderRepos;
import com.team.noty.event.repository.Updater;
import com.team.noty.event.repository.WriterRepos;
import com.team.noty.event.utils.callbacks.CallBack1;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UserUpdater implements Updater<User, Integer> {

    private ReaderRepos<User, Integer> readerRepos;
    private WriterRepos<User> writerRepos;

    @Inject
    UserUpdater(ReaderRepos<User, Integer> readerRepos, WriterRepos<User> writerRepos) {
        this.readerRepos = readerRepos;
        this.writerRepos = writerRepos;
    }

    @Override
    public Completable update(Integer id, CallBack1<User> updating) {
        return readerRepos.request(id)
                .doOnSuccess(updating::call)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapCompletable(writerRepos::edit);
    }
}
