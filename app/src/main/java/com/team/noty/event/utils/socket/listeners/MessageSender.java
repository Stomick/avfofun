package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.utils.socket.models.MessageReceive;
import com.team.noty.event.utils.socket.models.MessageSend;
import com.team.noty.event.utils.socket.models.ModelConnect;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.Ack;
import io.socket.client.Socket;

public class MessageSender implements Interactor<Single<MessageReceive>, MessageSend> {

    private Socket socket;
    private ModelConnect model;
    private Gson gson;
    private MessageSend message;
    private SingleEmitter<MessageReceive> emitter;

    @Inject
    public MessageSender(Socket socket, ModelConnect model, Gson gson) {
        this.socket = socket;
        this.model = model;
        this.gson = gson;
    }

    @Override
    public Single<MessageReceive> call(MessageSend message) {
        message.userId = model.userId;
        this.message = message;
        return Single.create(this::create)
                .subscribeOn(Schedulers.io());
    }

    private void create(SingleEmitter<MessageReceive> emitter){
        this.emitter = emitter;
        if (socket.connected())
            socket.emit(model.getEvent(message), gson.toJson(message), (Ack)this::sendAck);
    }

    private void sendAck(Object... args) {
        if (!emitter.isDisposed()) {
            if (args.length > 0 && args[0] != null) {
                String json = String.valueOf(args[0]);
                Error error = gson.fromJson(json, Error.class);
                error(String.valueOf(error.code) + " " + error.message);
                return;
            }
            if (args.length > 1 && args[1] != null) {
                String json = String.valueOf(args[1]);
                success(gson.fromJson(json, Info.class).result);
            }
        }
    }

    private void success(MessageReceive messageReceive) {
        messageReceive.update = this.message.update;
        emitter.onSuccess(messageReceive);
    }

    private void error(String message) {
        emitter.onError(new Throwable(message));
    }

    private class Error {
        public String message;
        public int code;
    }

    private class Info {
        public MessageReceive result;
    }
}
