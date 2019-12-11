package evfor.fun.skvader.repository.user;

import evfor.fun.skvader.models.User;
import evfor.fun.skvader.repository.ReaderRepos;
import evfor.fun.skvader.repository.Updater;
import evfor.fun.skvader.repository.WriterRepos;
import evfor.fun.skvader.utils.callbacks.CallBack1;

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
