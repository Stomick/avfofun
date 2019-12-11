package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.team.noty.event.utils.socket.models.MessageListReaded;
import com.team.noty.event.utils.socket.models.ModelConnect;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.socket.client.Socket;

public class MessageReadListener extends ObsSocketListener<MessageListReaded> {

    @Inject
    public MessageReadListener(Socket socket, ModelConnect model, Gson gson) {
        super(socket, model, gson);
    }

    @Override
    protected String event() {
        return model.readEvent();
    }

    protected void call(Object... args) {
        if (args.length > 0) {
            MessageListReaded messageListReaded = gson.fromJson(String.valueOf(args[0]), MessageListReaded.class);
            if (!emitter.isDisposed())
                emitter.onNext(messageListReaded);
        }
    }
}
