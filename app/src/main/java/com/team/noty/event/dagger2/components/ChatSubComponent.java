package com.team.noty.event.dagger2.components;

import com.team.noty.event.dagger2.modules.ChatModule;
import com.team.noty.event.dagger2.modules.SocketModule;
import com.team.noty.event.dagger2.scopes.ChatScope;
import com.team.noty.event.utils.socket.SocketMessenger;

import dagger.Subcomponent;

@Subcomponent(modules = {ChatModule.class, SocketModule.class})
@ChatScope
public interface ChatSubComponent {
    void inject(SocketMessenger socketMessenger);
}
