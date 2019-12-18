// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.dagger2.modules;

import android.content.Context;
import android.location.LocationManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class AppModule_ProvideLocationManagerFactory implements Factory<LocationManager> {
  private final AppModule module;

  private final Provider<Context> contextProvider;

  public AppModule_ProvideLocationManagerFactory(
      AppModule module, Provider<Context> contextProvider) {
    assert module != null;
    this.module = module;
    assert contextProvider != null;
    this.contextProvider = contextProvider;
  }

  @Override
  public LocationManager get() {
    return Preconditions.checkNotNull(
        module.provideLocationManager(contextProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<LocationManager> create(
      AppModule module, Provider<Context> contextProvider) {
    return new AppModule_ProvideLocationManagerFactory(module, contextProvider);
  }

  /** Proxies {@link AppModule#provideLocationManager(Context)}. */
  public static LocationManager proxyProvideLocationManager(AppModule instance, Context context) {
    return instance.provideLocationManager(context);
  }
}