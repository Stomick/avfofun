package evfor.fun.skvader.utils.socket.listeners;

import evfor.fun.skvader.utils.socket.models.SocketState;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.Socket;

public class SocketStateListener implements iSocketListener<Observable<SocketState>> {

    private Socket socket;
    private ObservableEmitter<SocketState> emitter;

    @Inject
    public SocketStateListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Observable<SocketState> call() {
        return Observable.create(this::create)
                .subscribeOn(Schedulers.io());
    }

    private void create(ObservableEmitter<SocketState> emitter) {
        this.emitter = emitter;
        socket.on(Socket.EVENT_CONNECT, args -> connect());
        socket.on(Socket.EVENT_DISCONNECT, args -> disconnect());
    }

    private void connect() {
        if (emitter != null && !emitter.isDisposed())
            this.emitter.onNext(new SocketState(true));
    }

    private void disconnect() {
        if (emitter != null && !emitter.isDisposed())
            this.emitter.onNext(new SocketState(false));
    }
}
