package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.team.noty.event.utils.socket.models.ModelConnect;
import com.team.noty.event.utils.socket.models.Writer;

import javax.inject.Inject;

import io.socket.client.Socket;

public class WriteMessageListener extends ObsSocketListener<Writer> {
    
    @Inject
    public WriteMessageListener(Socket socket, ModelConnect model, Gson gson) {
        super(socket, model, gson);
    }

    @Override
    protected String event() {
        return model.writeEvent();
    }

    @Override
    protected void call(Object... args) {
        if (!emitter.isDisposed() && args.length > 0) {
            Writer writer = gson.fromJson(String.valueOf(args[0]), Writer.class);
            emitter.onNext(writer);
        }
    }
}
