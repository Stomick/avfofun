package com.team.noty.event.utils.socket;

import android.util.Log;

import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Message;
import com.team.noty.event.utils.socket.listeners.iSocketListener;
import com.team.noty.event.utils.socket.models.MessageListReaded;
import com.team.noty.event.utils.socket.models.MessageRead;
import com.team.noty.event.utils.socket.models.MessageReceive;
import com.team.noty.event.utils.socket.models.MessageSend;
import com.team.noty.event.utils.socket.models.ModelConnect;
import com.team.noty.event.utils.socket.models.SocketState;
import com.team.noty.event.utils.socket.models.Writer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static com.team.noty.event.ui.activities.DialogActivity.isNeedToConnect;

public class SocketMessenger {
    @Inject
    iSocketListener<Observable<Message>> messageObserver;
    @Inject
    iSocketListener<Observable<SocketState>> socketStateListener;
    @Inject
    Interactor<Single<MessageReceive>, MessageSend> messageSender;
    @Inject
    iSocketListener<Observable<MessageListReaded>> readListener;
    @Inject
    iSocketListener<Observable<Writer>> writeListener;
    @Inject
    iSocketListener<Completable> writing;
    @Inject
    Interactor<Completable, MessageRead> readMessages;
    @Inject
    Socket socket;

    public void init(String eventId, String userId, boolean isEvent) {
        Injector.get().getChatComponent(new ModelConnect(eventId, userId, isEvent)).inject(this);
    }

    public void init(String eventId, boolean isEvent) {
        init(eventId, null, isEvent);
    }

    public void connect() {
        socket.connect();
    }

    public Observable<SocketState> socketStateObservable() {
        return socketStateListener.call()
                .doOnNext(this::reconnect);
    }

    private void reconnect(SocketState state) {
        if (!state.isConnected && isNeedToConnect)
            connect();
    }

    public void disconnect() {
        if (socket != null)
            socket.disconnect();
    }

    public Single<MessageReceive> sendTextMessage(String message, int update) {
        return messageSender.call(MessageSend.TextMessage(message, update));
    }

    public Single<MessageReceive> sendPhotoMessage(String base64, int update) {
        return messageSender.call(MessageSend.PhotoMessage(base64, update));
    }

    public Single<MessageReceive> sendVoiceMessage(byte[] voiceBytes, int update) {
        return messageSender.call(MessageSend.VoiceMessage(voiceBytes, update));
    }

    public Observable<Message> messageObservable() {
        return messageObserver.call();
    }

    public Observable<MessageListReaded> readMessageObservable() {
        return readListener.call();
    }

    public Observable<Writer> writeObserver() {
        return writeListener.call();
    }

    public void writeEmit() {
        writing.call().subscribe();
    }

    public void readMessages(List<String> messagesIds, String userId) {
        readMessages.call(new MessageRead(userId, messagesIds))
                .subscribe(() ->{
                },throwable -> {
                    Log.e("my",throwable.getMessage());
                });
    }
}
