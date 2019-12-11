package evfor.fun.skvader.repository;

import android.support.annotation.NonNull;

import com.pacoworks.rxpaper2.RxPaperBook;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PaperRepos<Out extends Identified> implements LocalRepository<Out, Integer> {

    private RxPaperBook paperBook;

    @Inject
    PaperRepos() {
        paperBook = RxPaperBook.with(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Out> request(@NonNull Integer key) {
        return paperBook.read(String.valueOf(key));
    }

    @Override
    public Completable edit(@NonNull Out new_) {
        return paperBook.write(new_.id(), new_);
    }
}
