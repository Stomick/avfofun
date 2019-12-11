package evfor.fun.skvader.interceptors;

import com.pacoworks.rxpaper2.RxPaperBook;
import evfor.fun.skvader.exceptions.NotFoundException;
import evfor.fun.skvader.models.ActId;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class BanedActList implements AsyncList<ActId> {

    private static final String BAN = "ban";

    private RxPaperBook book;

    @Inject
    BanedActList() {
        book = RxPaperBook.with(BAN, Schedulers.io());
    }

    @Override
    public Completable contain(ActId actId) {
        return book.read(actId.id, new ActId())
                .flatMapCompletable(this::checkSearch);
    }

    @Override
    public Completable add(ActId actId){
        return book.write(actId.id(), actId);
    }

    @Override
    public Completable remove(ActId actId){
        return book.delete(actId.id());
    }

    private Completable checkSearch(ActId actId) {
        if(actId.id.isEmpty())
            return Completable.error(new NotFoundException(actId.toString()));
        else return Completable.complete();
    }


}
