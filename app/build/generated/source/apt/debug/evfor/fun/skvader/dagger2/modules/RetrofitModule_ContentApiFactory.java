// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.dagger2.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import evfor.fun.skvader.network.api.ContentApi;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class RetrofitModule_ContentApiFactory implements Factory<ContentApi> {
  private final Provider<Retrofit> retrofitProvider;

  public RetrofitModule_ContentApiFactory(Provider<Retrofit> retrofitProvider) {
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ContentApi get() {
    return Preconditions.checkNotNull(
        RetrofitModule.contentApi(retrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ContentApi> create(Provider<Retrofit> retrofitProvider) {
    return new RetrofitModule_ContentApiFactory(retrofitProvider);
  }
}