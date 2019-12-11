package com.team.noty.event.dagger2.modules;

import com.team.noty.event.app.AuthData;
import com.team.noty.event.dagger2.scopes.ChatScope;
import com.team.noty.event.network.URLS;
import com.team.noty.event.utils.socket.models.ModelConnect;

import java.net.URI;

import dagger.Module;
import dagger.Provides;
import io.socket.client.IO;
import io.socket.client.Socket;

@Module
public class ChatModule {

    private ModelConnect model;

    public ChatModule(ModelConnect model) {
        this.model = model;
    }

    @Provides
    @ChatScope
    public ModelConnect provideModelConnect(){
        return model;
    }


    @Provides
    @ChatScope
    Socket provideSocketClient(IO.Options options) {
        return IO.socket(URI.create(URLS.ChatBaseSocket), options);
    }

    @Provides
    @ChatScope
    IO.Options provideSocketClientOptions() {
        IO.Options options = new IO.Options();
        if (model != null)
            options.query = "token=" + AuthData.getToken() + "&eventId=" + model.eventId + "&type=" + model.getType();
        options.secure = true;
        return options;
    }

}
