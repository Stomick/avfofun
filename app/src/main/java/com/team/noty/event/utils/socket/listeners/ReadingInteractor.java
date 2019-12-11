package com.team.noty.event.utils.socket.listeners;

import com.google.gson.Gson;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.utils.Log;
import com.team.noty.event.utils.socket.models.MessageRead;
import com.team.noty.event.utils.socket.models.ModelConnect;

import org.json.JSONObject;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.socket.client.Ack;
import io.socket.client.Socket;

public class ReadingInteractor implements Interactor<Completable, MessageRead> {

    private Socket socket;
    private ModelConnect model;
    private Gson gson;
    private CompletableEmitter emitter;
    private MessageRead messageRead;

    @Inject
    public ReadingInteractor(Socket socket, ModelConnect model, Gson gson) {
        this.socket = socket;
        this.model = model;
        this.gson = gson;
    }

    @Override
    public Completable call(MessageRead messageRead) {
        this.messageRead = messageRead;
        return Completable.create(this::read);
    }

    private void read(CompletableEmitter emitter) {
        this.emitter = emitter;
        String jsonObject = gson.toJson(messageRead);
        if (socket.connected())
            socket.emit(model.readEvent(),
                    jsonObject);
    }
}
