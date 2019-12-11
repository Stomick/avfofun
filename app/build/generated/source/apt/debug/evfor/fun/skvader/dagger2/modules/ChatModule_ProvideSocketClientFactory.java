// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.dagger2.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.socket.client.IO;
import io.socket.client.Socket;
import javax.inject.Provider;

public final class ChatModule_ProvideSocketClientFactory implements Factory<Socket> {
  private final ChatModule module;

  private final Provider<IO.Options> optionsProvider;

  public ChatModule_ProvideSocketClientFactory(
      ChatModule module, Provider<IO.Options> optionsProvider) {
    assert module != null;
    this.module = module;
    assert optionsProvider != null;
    this.optionsProvider = optionsProvider;
  }

  @Override
  public Socket get() {
    return Preconditions.checkNotNull(
        module.provideSocketClient(optionsProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Socket> create(ChatModule module, Provider<IO.Options> optionsProvider) {
    return new ChatModule_ProvideSocketClientFactory(module, optionsProvider);
  }

  /** Proxies {@link ChatModule#provideSocketClient(IO.Options)}. */
  public static Socket proxyProvideSocketClient(ChatModule instance, IO.Options options) {
    return instance.provideSocketClient(options);
  }
}