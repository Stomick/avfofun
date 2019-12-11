package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.team.noty.event.utils.socket.models.ModelConnect;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.Socket;

public abstract class ObsSocketListener<T> implements iSocketListener<Observable<T>> {

    protected Socket socket;
    protected ModelConnect model;
    protected Gson gson;
    protected ObservableEmitter<T> emitter;

    public ObsSocketListener(Socket socket, ModelConnect model, Gson gson) {
        this.socket = socket;
        this.model = model;
        this.gson = gson;
    }

    @Override
    public Observable<T> call() {
        return Observable.create(this::create)
                .subscribeOn(Schedulers.io());
    }

    protected void create(ObservableEmitter<T> emitter){
        this.emitter = emitter;
        socket.on(event(), this::call);
    }

    protected abstract String event();

    protected abstract void call(Object... args);
}
