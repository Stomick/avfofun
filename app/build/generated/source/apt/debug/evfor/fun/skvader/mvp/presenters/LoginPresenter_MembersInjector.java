// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import javax.inject.Provider;

public final class LoginPresenter_MembersInjector implements MembersInjector<LoginPresenter> {
  private final Provider<LoginApi> apiProvider;

  private final Provider<AccountPreferenceManager> accountPreferenceManagerProvider;

  public LoginPresenter_MembersInjector(
      Provider<LoginApi> apiProvider,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert accountPreferenceManagerProvider != null;
    this.accountPreferenceManagerProvider = accountPreferenceManagerProvider;
  }

  public static MembersInjector<LoginPresenter> create(
      Provider<LoginApi> apiProvider,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    return new LoginPresenter_MembersInjector(apiProvider, accountPreferenceManagerProvider);
  }

  @Override
  public void injectMembers(LoginPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.api = apiProvider.get();
    instance.accountPreferenceManager = accountPreferenceManagerProvider.get();
  }

  public static void injectApi(LoginPresenter instance, Provider<LoginApi> apiProvider) {
    instance.api = apiProvider.get();
  }

  public static void injectAccountPreferenceManager(
      LoginPresenter instance,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    instance.accountPreferenceManager = accountPreferenceManagerProvider.get();
  }
}