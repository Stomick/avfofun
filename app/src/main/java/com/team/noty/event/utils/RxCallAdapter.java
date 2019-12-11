package com.team.noty.event.utils;

import android.support.annotation.NonNull;

import com.team.noty.event.network.models.response.RsBase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class RxCallAdapter extends CallAdapter.Factory {
    private final CallAdapter.Factory original;
    private iHandler<Throwable> errorHandler;

    @Inject
    public RxCallAdapter(CallAdapter.Factory original, iHandler<Throwable> errorHandler) {
        this.original = original;
        this.errorHandler = errorHandler;
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        return new RxCallAdapterWrapper(original.get(returnType, annotations, retrofit));
    }

    private class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final CallAdapter<R, Object> wrapped;

        RxCallAdapterWrapper(CallAdapter<R, Object> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @Override
        public Object adapt(@NonNull Call<R> call) {
            Object result = wrapped.adapt(call);
//            if (result instanceof Single)
//                return ((Single<?>) result)
//                        .doOnSuccess(this::rsBaseHandler)
//                        //.doOnError(throwable -> android.util.Log.e("my",throwable.getMessage()))
//                        .onErrorResumeNext(Single.never());
//            if (result instanceof Observable)
//                return ((Observable<?>) result)
//                        .doOnNext(this::rsBaseHandler)
//                        //.doOnError(throwable -> android.util.Log.e("my",throwable.getMessage()))
//                        .onErrorResumeNext(Observable.empty());
//            if (result instanceof Completable)
//                return ((Completable) result)
//                        //.doOnError(throwable -> android.util.Log.e("my",throwable.getMessage()))
//                        .onErrorResumeNext(throwable -> Completable.never());
            return result;
        }

        private void rsBaseHandler(Object o) throws Exception {
//            if(o instanceof RsBase)
//                if(!((RsBase) o).success)
//                    throw new Exception(((RsBase) o).message);
        }
    }
}
