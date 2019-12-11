package evfor.fun.skvader.dagger2.modules;

import evfor.fun.skvader.dagger2.scopes.ChatScope;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Message;
import evfor.fun.skvader.utils.socket.Socket;
import evfor.fun.skvader.utils.socket.SocketImpl;
import evfor.fun.skvader.utils.socket.listeners.MessageReadListener;
import evfor.fun.skvader.utils.socket.listeners.MessageReceiver;
import evfor.fun.skvader.utils.socket.listeners.MessageSender;
import evfor.fun.skvader.utils.socket.listeners.ReadingInteractor;
import evfor.fun.skvader.utils.socket.listeners.SocketStateListener;
import evfor.fun.skvader.utils.socket.listeners.WriteMessageListener;
import evfor.fun.skvader.utils.socket.listeners.WritingInteractor;
import evfor.fun.skvader.utils.socket.listeners.iSocketListener;
import evfor.fun.skvader.utils.socket.models.MessageListReaded;
import evfor.fun.skvader.utils.socket.models.MessageRead;
import evfor.fun.skvader.utils.socket.models.MessageReceive;
import evfor.fun.skvader.utils.socket.models.MessageSend;
import evfor.fun.skvader.utils.socket.models.SocketState;
import evfor.fun.skvader.utils.socket.models.Writer;

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
