package evfor.fun.skvader.dagger2.modules;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.dagger2.scopes.ChatScope;
import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.utils.socket.models.ModelConnect;

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
            options.query = "token=" + AuthData.getToken();
        options.secure = true;
        return options;
    }

}
