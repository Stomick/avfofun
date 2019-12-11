package com.team.noty.event.network;

import com.team.noty.event.utils.Log;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.subjects.SingleSubject;

public class SingleRequest<Rq, Rs> implements RequestController<Rq, Rs> {
    private Map<Rq, SingleSubject<Rs>> requestMap;

    @Inject
    SingleRequest() {
        this.requestMap = new HashMap<>();
    }

    @Override
    public synchronized Single<Rs> request(Rq rq, Single<Rs> request) {
        SingleSubject<Rs> subject;
        if (!requestMap.containsKey(rq)) {
            Log.d(getClass(), "new subject key = " + String.valueOf(rq));
            subject = SingleSubject.create();
            request.zipWith(Single.just(rq), this::deleteAndGet).subscribe(subject);
            requestMap.put(rq, subject);
        } else {
            Log.d(getClass(), "contains key = " + String.valueOf(rq));
            subject = requestMap.get(rq);
        }
        return subject;
    }

    private Rs deleteAndGet(Rs rs, Rq rq) {
        Log.d(getClass(), "delete key = " + String.valueOf(rq));
        requestMap.remove(rq);
        return rs;
    }
}
