// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.utils.socket.listeners;

import com.google.gson.Gson;
import dagger.internal.Factory;
import evfor.fun.skvader.utils.socket.models.ModelConnect;
import io.socket.client.Socket;
import javax.inject.Provider;

public final class ReadingInteractor_Factory implements Factory<ReadingInteractor> {
  private final Provider<Socket> socketProvider;

  private final Provider<ModelConnect> modelProvider;

  private final Provider<Gson> gsonProvider;

  public ReadingInteractor_Factory(
      Provider<Socket> socketProvider,
      Provider<ModelConnect> modelProvider,
      Provider<Gson> gsonProvider) {
    assert socketProvider != null;
    this.socketProvider = socketProvider;
    assert modelProvider != null;
    this.modelProvider = modelProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public ReadingInteractor get() {
    return new ReadingInteractor(socketProvider.get(), modelProvider.get(), gsonProvider.get());
  }

  public static Factory<ReadingInteractor> create(
      Provider<Socket> socketProvider,
      Provider<ModelConnect> modelProvider,
      Provider<Gson> gsonProvider) {
    return new ReadingInteractor_Factory(socketProvider, modelProvider, gsonProvider);
  }
}