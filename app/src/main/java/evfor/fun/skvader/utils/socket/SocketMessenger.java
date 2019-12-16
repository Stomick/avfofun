package evfor.fun.skvader.utils.socket;

import android.util.Log;

import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.utils.socket.listeners.iSocketListener;
import evfor.fun.skvader.utils.socket.models.MessageListReaded;
import evfor.fun.skvader.utils.socket.models.MessageRead;
import evfor.fun.skvader.utils.socket.models.MessageReceive;
import evfor.fun.skvader.utils.socket.models.MessageSend;
import evfor.fun.skvader.utils.socket.models.ModelConnect;
import evfor.fun.skvader.utils.socket.models.SocketState;
import evfor.fun.skvader.utils.socket.models.Writer;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.socket.client.IO;
import io.socket.client.Socket;

import static evfor.fun.skvader.ui.activities.DialogActivity.isNeedToConnect;

public class SocketMessenger {

    private static SocketMessenger chatSocket;

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

    public static void initInstance() {
        Log.d("MY", "MySingleton::InitInstance()");
        if (chatSocket == null) {
            chatSocket = new SocketMessenger();
        }
    }

    public static SocketMessenger getInstance() {
        Log.d("MY", "MySingleton::getInstance()");
        return chatSocket;
    }

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

    public void readMessages(List<String> messagesIds, Integer userId) {
        readMessages.call(new MessageRead(userId, messagesIds))
                .subscribe(() -> {
                }, throwable -> {
                    Log.e("my", throwable.getMessage());
                });
    }

    public void setSocket(String str){
        try {
            socket = IO.socket(str);
            Log.e("my", socket.id());
        } catch (URISyntaxException e) {
            Log.e("my", e.getMessage());

        }
    }

}
