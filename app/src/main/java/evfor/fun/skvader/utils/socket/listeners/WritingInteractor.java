package evfor.fun.skvader.utils.socket.listeners;

import evfor.fun.skvader.utils.socket.models.ModelConnect;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.socket.client.Ack;
import io.socket.client.Socket;

public class WritingInteractor implements iSocketListener<Completable> {

    private Socket socket;
    private ModelConnect model;
    private CompletableEmitter emitter;

    @Inject
    public WritingInteractor(Socket socket, ModelConnect model) {
        this.socket = socket;
        this.model = model;
    }

    @Override
    public Completable call() {
        return Completable.create(this::write);
    }

    private void write(CompletableEmitter emitter) {
        this.emitter = emitter;
        if (socket != null && socket.connected())
            if (model.userId == null)
                socket.emit(model.writeEvent(), "{}",
                        (Ack) args -> this.emitter.onComplete());
            else socket.emit(model.writeEvent(), "{\"userId\":\"" + model.userId + "\"}",
                    (Ack) args -> this.emitter.onComplete());
    }
}
