// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.network.api.MainApi;
import javax.inject.Provider;

public final class ComplaintPresenter_MembersInjector
    implements MembersInjector<ComplaintPresenter> {
  private final Provider<MainApi> apiProvider;

  public ComplaintPresenter_MembersInjector(Provider<MainApi> apiProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
  }

  public static MembersInjector<ComplaintPresenter> create(Provider<MainApi> apiProvider) {
    return new ComplaintPresenter_MembersInjector(apiProvider);
  }

  @Override
  public void injectMembers(ComplaintPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.api = apiProvider.get();
  }

  public static void injectApi(ComplaintPresenter instance, Provider<MainApi> apiProvider) {
    instance.api = apiProvider.get();
  }
}