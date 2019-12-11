// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.interceptors;

import dagger.internal.Factory;
import evfor.fun.skvader.network.api.MainApi;
import javax.inject.Provider;

public final class AdministrationAct_Factory implements Factory<AdministrationAct> {
  private final Provider<MainApi> apiProvider;

  public AdministrationAct_Factory(Provider<MainApi> apiProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  @Override
  public AdministrationAct get() {
    return new AdministrationAct(apiProvider.get());
  }

  public static Factory<AdministrationAct> create(Provider<MainApi> apiProvider) {
    return new AdministrationAct_Factory(apiProvider);
  }

  /** Proxies {@link AdministrationAct#AdministrationAct(MainApi)}. */
  public static AdministrationAct newAdministrationAct(MainApi api) {
    return new AdministrationAct(api);
  }
}