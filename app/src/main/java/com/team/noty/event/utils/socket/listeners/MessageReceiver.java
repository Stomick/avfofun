package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.team.noty.event.models.Message;
import com.team.noty.event.utils.DateFormatter;
import com.team.noty.event.utils.socket.models.MessageTake;
import com.team.noty.event.utils.socket.models.ModelConnect;

import javax.inject.Inject;

import io.reactivex.ObservableEmitter;
import io.socket.client.Socket;

public class MessageReceiver extends ObsSocketListener<Message> {

    @Inject
    public MessageReceiver(Socket socket, ModelConnect model, Gson gson) {
        super(socket, model, gson);
    }

    @Override
    protected void create(ObservableEmitter<Message> emitter) {
        this.emitter = emitter;
        socket.on(model.messageEvent(), this::call);
        socket.on(model.audioEvent(), this::call);
        socket.on(model.photoEvent(), this::call);
    }

    @Override
    protected String event() {
        return null;
    }

    @Override
    protected void call(Object... args) {
        if (args.length > 0 && !emitter.isDisposed()) { Message message = convert(gson.fromJson(String.valueOf(args[0]), MessageTake.class));
            if (message != null)
                emitter.onNext(message);
            else
                emitter.onError(new JsonParseException(String.valueOf(args[0])));
        }
    }

    private Message convert(MessageTake messageTake) {
        if(messageTake == null)
            return null;
        Message message = new Message();
        message.id = messageTake.messageId;
        message.sender = messageTake.userId;
        message.date = DateFormatter.fromIso(messageTake.date);
        message.recipient = messageTake.recipient;
        message.body = messageTake.getMessage();
        message.type = messageTake.type() == 0 ? Message.Type.TEXT :
                (messageTake.type() == 1 ? Message.Type.VOICE : Message.Type.IMAGE);
        return message;
    }
}
