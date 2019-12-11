// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.app;

import dagger.MembersInjector;
import evfor.fun.skvader.network.api.LoginApi;
import javax.inject.Provider;

public final class App_MembersInjector implements MembersInjector<App> {
  private final Provider<LoginApi> apiProvider;

  public App_MembersInjector(Provider<LoginApi> apiProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  public static MembersInjector<App> create(Provider<LoginApi> apiProvider) {
    return new App_MembersInjector(apiProvider);
  }

  @Override
  public void injectMembers(App instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.api = apiProvider.get();
  }

  public static void injectApi(App instance, Provider<LoginApi> apiProvider) {
    instance.api = apiProvider.get();
  }
}