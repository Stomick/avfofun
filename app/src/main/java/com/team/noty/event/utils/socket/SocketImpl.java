package com.team.noty.event.utils.socket;

import javax.inject.Inject;

public class SocketImpl implements Socket {

    private io.socket.client.Socket socket;

    @Inject
    SocketImpl(io.socket.client.Socket socket) {
        this.socket = socket;
    }

    @Override
    public void connect() {
        if (!socket.connected())
            socket.connect();
    }

    @Override
    public void disconnect() {
        if (socket.connected())
            socket.disconnect();
    }
}
