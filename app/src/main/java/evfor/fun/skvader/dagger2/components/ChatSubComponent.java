package evfor.fun.skvader.dagger2.components;

import evfor.fun.skvader.dagger2.modules.ChatModule;
import evfor.fun.skvader.dagger2.modules.SocketModule;
import evfor.fun.skvader.dagger2.scopes.ChatScope;
import evfor.fun.skvader.utils.socket.SocketMessenger;

import dagger.Subcomponent;

@Subcomponent(modules = {ChatModule.class, SocketModule.class})
@ChatScope
public interface ChatSubComponent {
    void inject(SocketMessenger socketMessenger);
}
