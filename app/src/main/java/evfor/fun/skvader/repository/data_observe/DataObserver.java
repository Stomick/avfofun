package evfor.fun.skvader.repository.data_observe;

import io.reactivex.Observable;

public interface DataObserver<Data> {
    Observable<Data> observe();
}
