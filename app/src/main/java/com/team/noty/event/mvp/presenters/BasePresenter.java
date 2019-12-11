package com.team.noty.event.mvp.presenters;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.MvpPresenter;
import com.team.noty.event.R;
import com.team.noty.event.mvp.views.BaseView;
import com.team.noty.event.network.models.response.RsBase;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

@SuppressLint("CheckResult")
abstract class BasePresenter<T extends BaseView> extends MvpPresenter<T> {

    void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    public static <E extends RsBase> Observable<E> api(Observable<E> observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }

    public static <E extends RsBase> Single<E> api(Single<E> observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }

    public static <S> Observable<S> androidThreadObserveOn(Observable<S> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    public static <S> Maybe<S> androidThreadObserveOn(Maybe<S> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    public static <S> Observable<S> ioSubscribeOn(Observable<S> observable) {
        return observable.subscribeOn(Schedulers.io());
    }

    public static <S> Maybe<S> ioSubscribeOn(Maybe<S> observable) {
        return observable.subscribeOn(Schedulers.io());
    }

    public static Completable androidThreadObserveOn(Completable observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    public static Completable ioSubscribeOn(Completable observable) {
        return observable.subscribeOn(Schedulers.io());
    }

    public static <S> Single<S> androidThreadObserveOn(Single<S> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread());
    }

    public static <S> Single<S> ioSubscribeOn(Single<S> observable) {
        return observable.subscribeOn(Schedulers.io());
    }

    public static <S> Observable<S> subIoObsMain(Observable<S> observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }

    public static <S> Single<S> subIoObsMain(Single<S> observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }

    public static <S> Maybe<S> subIoObsMain(Maybe<S> observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }

    public static Completable subIoObsMain(Completable observable) {
        return ioSubscribeOn(androidThreadObserveOn(observable));
    }
}
