// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.utils.socket.listeners;

import com.google.gson.Gson;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import evfor.fun.skvader.utils.socket.models.ModelConnect;
import io.socket.client.Socket;
import javax.inject.Provider;

public final class MessageReadListener_Factory implements Factory<MessageReadListener> {
  private final MembersInjector<MessageReadListener> messageReadListenerMembersInjector;

  private final Provider<Socket> socketProvider;

  private final Provider<ModelConnect> modelProvider;

  private final Provider<Gson> gsonProvider;

  public MessageReadListener_Factory(
      MembersInjector<MessageReadListener> messageReadListenerMembersInjector,
      Provider<Socket> socketProvider,
      Provider<ModelConnect> modelProvider,
      Provider<Gson> gsonProvider) {
    assert messageReadListenerMembersInjector != null;
    this.messageReadListenerMembersInjector = messageReadListenerMembersInjector;
    assert socketProvider != null;
    this.socketProvider = socketProvider;
    assert modelProvider != null;
    this.modelProvider = modelProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public MessageReadListener get() {
    return MembersInjectors.injectMembers(
        messageReadListenerMembersInjector,
        new MessageReadListener(socketProvider.get(), modelProvider.get(), gsonProvider.get()));
  }

  public static Factory<MessageReadListener> create(
      MembersInjector<MessageReadListener> messageReadListenerMembersInjector,
      Provider<Socket> socketProvider,
      Provider<ModelConnect> modelProvider,
      Provider<Gson> gsonProvider) {
    return new MessageReadListener_Factory(
        messageReadListenerMembersInjector, socketProvider, modelProvider, gsonProvider);
  }
}