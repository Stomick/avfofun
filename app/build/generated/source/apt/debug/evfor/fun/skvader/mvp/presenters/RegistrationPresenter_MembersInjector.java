// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.network.api.RegistrationApi;
import evfor.fun.skvader.utils.AccountPreferenceManager;
import javax.inject.Provider;

public final class RegistrationPresenter_MembersInjector
    implements MembersInjector<RegistrationPresenter> {
  private final Provider<RegistrationApi> apiProvider;

  private final Provider<AccountPreferenceManager> accountPreferenceManagerProvider;

  public RegistrationPresenter_MembersInjector(
      Provider<RegistrationApi> apiProvider,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert accountPreferenceManagerProvider != null;
    this.accountPreferenceManagerProvider = accountPreferenceManagerProvider;
  }

  public static MembersInjector<RegistrationPresenter> create(
      Provider<RegistrationApi> apiProvider,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    return new RegistrationPresenter_MembersInjector(apiProvider, accountPreferenceManagerProvider);
  }

  @Override
  public void injectMembers(RegistrationPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.api = apiProvider.get();
    instance.accountPreferenceManager = accountPreferenceManagerProvider.get();
  }

  public static void injectApi(
      RegistrationPresenter instance, Provider<RegistrationApi> apiProvider) {
    instance.api = apiProvider.get();
  }

  public static void injectAccountPreferenceManager(
      RegistrationPresenter instance,
      Provider<AccountPreferenceManager> accountPreferenceManagerProvider) {
    instance.accountPreferenceManager = accountPreferenceManagerProvider.get();
  }
}