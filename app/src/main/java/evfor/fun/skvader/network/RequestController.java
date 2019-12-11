package evfor.fun.skvader.network;

import io.reactivex.Single;

public interface RequestController<Rq, Rs> {
    Single<Rs> request(Rq rq, Single<Rs> request);
}
