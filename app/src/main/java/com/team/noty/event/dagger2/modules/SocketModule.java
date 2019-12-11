package com.team.noty.event.dagger2.modules;

import com.team.noty.event.dagger2.scopes.ChatScope;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.models.Message;
import com.team.noty.event.utils.socket.Socket;
import com.team.noty.event.utils.socket.SocketImpl;
import com.team.noty.event.utils.socket.listeners.MessageReadListener;
import com.team.noty.event.utils.socket.listeners.MessageReceiver;
import com.team.noty.event.utils.socket.listeners.MessageSender;
import com.team.noty.event.utils.socket.listeners.ReadingInteractor;
import com.team.noty.event.utils.socket.listeners.SocketStateListener;
import com.team.noty.event.utils.socket.listeners.WriteMessageListener;
import com.team.noty.event.utils.socket.listeners.WritingInteractor;
import com.team.noty.event.utils.socket.listeners.iSocketListener;
import com.team.noty.event.utils.socket.models.MessageListReaded;
import com.team.noty.event.utils.socket.models.MessageRead;
import com.team.noty.event.utils.socket.models.MessageReceive;
import com.team.noty.event.utils.socket.models.MessageSend;
import com.team.noty.event.utils.socket.models.SocketState;
import com.team.noty.event.utils.socket.models.Writer;

import dagger.Binds;
import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Module
public interface SocketModule {

    @Binds
    @ChatScope
    Socket provideSocket(SocketImpl socket);

    @Binds
    @ChatScope
    iSocketListener<Observable<Message>> messageObserver(MessageReceiver messageReceiver);

    @Binds
    @ChatScope
    iSocketListener<Observable<SocketState>> socketStateListener(SocketStateListener socketStateListener);

    @Binds
    @ChatScope
    Interactor<Single<MessageReceive>, MessageSend> messageSender(MessageSender messageSender);

    @Binds
    @ChatScope
    iSocketListener<Observable<MessageListReaded>> readListener(MessageReadListener messageReadListener);

    @Binds
    @ChatScope
    iSocketListener<Observable<Writer>> writeListener(WriteMessageListener writeMessageListener);

    @Binds
    @ChatScope
    iSocketListener<Completable> writing(WritingInteractor writingInteractor);

    @Binds
    @ChatScope
    Interactor<Completable, MessageRead> readMessages(ReadingInteractor readingInteractor);
}
