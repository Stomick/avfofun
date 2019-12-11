package evfor.fun.skvader.interceptors;

import android.annotation.SuppressLint;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.models.RqChat;
import evfor.fun.skvader.network.api.ChatApi;
import evfor.fun.skvader.network.models.response.RsMessage;
import evfor.fun.skvader.utils.DateFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MessagesLoaderInteractor implements Interactor<Observable<Message>, RqChat> {

    private ChatApi api;
    List<String> list_id = new ArrayList<>();
    @Inject
    MessagesLoaderInteractor(ChatApi api) {
        this.api = api;
    }

    @Override
    public Observable<Message> call(RqChat rqChat) {
        return (rqChat.userId == null
                ? api.getEventMessages(rqChat.actId.id(), rqChat.actId.type())
                : api.getUserMessages(rqChat.actId.id(), rqChat.userId.id(), rqChat.actId.type()))
                .flatMap(this::emptyHandle)
                .map(this::convert);
    }

    @SuppressLint("CheckResult")
    private Observable<RsMessage> emptyHandle(List<RsMessage> messages){
        for (RsMessage it: messages) {
            if(!it.isRead)
                list_id.add(it._id);
        }

        if(messages.isEmpty())
            return Observable.empty();
        else return Observable.fromIterable(messages);
    }

    private Message convert(RsMessage rsMessage) {
        Message message = new Message();
        message.type = getType(rsMessage);
        message.sender = rsMessage.getSender();
        message.recipient = rsMessage.recipient;
        if (AuthData.equalId(rsMessage.getSender())) {
            if (rsMessage.isRead)
                message.status = Message.Status.READ;
            else message.status = Message.Status.DELIVER;
        }
        message.body = getBody(rsMessage);
        message.date = DateFormatter.fromIso(rsMessage.createdAt);
        message.id = rsMessage._id;
        return message;
    }

    private String getBody(RsMessage message) {
        if (message.photoUrl != null)
            return message.photoUrl;
        else if (message.audioUrl != null)
            return message.audioUrl;
        else return message.message;
    }

    private Message.Type getType(RsMessage message) {
        if (message.photoUrl != null)
            return Message.Type.IMAGE;
        else if (message.audioUrl != null)
            return Message.Type.VOICE;
        else return Message.Type.TEXT;
    }
}
