package evfor.fun.skvader.utils.socket;

import java.net.URISyntaxException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.socket.client.IO;
import io.socket.client.Socket;
import okhttp3.OkHttpClient;

public class SocketChat {


    private static volatile SocketChat instance;

    public static SocketChat getInstance() {
        SocketChat localInstance = instance;
        if (localInstance == null) {
            synchronized (SocketChat.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new SocketChat();
                }
            }
        }
        return localInstance;
    }
    private Socket chat;

    public Socket getChat() {
        return chat;
    }

    public Socket provideSocketChat(String url) {
        if(chat != null) {
            return chat;
        }

        IO.Options options = new IO.Options();
        options.transports = new String[] {"polling"};
        options.reconnectionDelay = 60000;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();

        IO.setDefaultOkHttpCallFactory(okHttpClient);
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient);

        try {
            chat = IO.socket(url);
        } catch (URISyntaxException e) {}

        chat.connect();
        return chat;
    }
}
