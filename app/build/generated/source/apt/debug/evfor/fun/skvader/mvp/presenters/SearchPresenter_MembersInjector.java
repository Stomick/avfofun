// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.interceptors.AsyncList;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.ActId;
import evfor.fun.skvader.models.ActUpdate;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.MainApi;
import io.reactivex.Completable;
import io.reactivex.Observable;
import javax.inject.Provider;

public final class SearchPresenter_MembersInjector implements MembersInjector<SearchPresenter> {
  private final Provider<ContentApi> apiProvider;

  private final Provider<MainApi> mainApiProvider;

  private final Provider<AsyncList<ActId>> banedActListProvider;

  private final Provider<Interactor<Observable<Act>, FilterModel>> filterInteractorProvider;

  private final Provider<Interactor<Completable, ActUpdate>> inOutInteractorProvider;

  public SearchPresenter_MembersInjector(
      Provider<ContentApi> apiProvider,
      Provider<MainApi> mainApiProvider,
      Provider<AsyncList<ActId>> banedActListProvider,
      Provider<Interactor<Observable<Act>, FilterModel>> filterInteractorProvider,
      Provider<Interactor<Completable, ActUpdate>> inOutInteractorProvider) {
    assert apiProvider != null;
    this.apiProvider = apiProvider;
    assert mainApiProvider != null;
    this.mainApiProvider = mainApiProvider;
    assert banedActListProvider != null;
    this.banedActListProvider = banedActListProvider;
    assert filterInteractorProvider != null;
    this.filterInteractorProvider = filterInteractorProvider;
    assert inOutInteractorProvider != null;
    this.inOutInteractorProvider = inOutInteractorProvider;
  }

  public static MembersInjector<SearchPresenter> create(
      Provider<ContentApi> apiProvider,
      Provider<MainApi> mainApiProvider,
      Provider<AsyncList<ActId>> banedActListProvider,
      Provider<Interactor<Observable<Act>, FilterModel>> filterInteractorProvider,
      Provider<Interactor<Completable, ActUpdate>> inOutInteractorProvider) {
    return new SearchPresenter_MembersInjector(
        apiProvider,
        mainApiProvider,
        banedActListProvider,
        filterInteractorProvider,
        inOutInteractorProvider);
  }

  @Override
  public void injectMembers(SearchPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.api = apiProvider.get();
    instance.mainApi = mainApiProvider.get();
    instance.banedActList = banedActListProvider.get();
    instance.filterInteractor = filterInteractorProvider.get();
    instance.inOutInteractor = inOutInteractorProvider.get();
  }

  public static void injectApi(SearchPresenter instance, Provider<ContentApi> apiProvider) {
    instance.api = apiProvider.get();
  }

  public static void injectMainApi(SearchPresenter instance, Provider<MainApi> mainApiProvider) {
    instance.mainApi = mainApiProvider.get();
  }

  public static void injectBanedActList(
      SearchPresenter instance, Provider<AsyncList<ActId>> banedActListProvider) {
    instance.banedActList = banedActListProvider.get();
  }

  public static void injectFilterInteractor(
      SearchPresenter instance,
      Provider<Interactor<Observable<Act>, FilterModel>> filterInteractorProvider) {
    instance.filterInteractor = filterInteractorProvider.get();
  }

  public static void injectInOutInteractor(
      SearchPresenter instance,
      Provider<Interactor<Completable, ActUpdate>> inOutInteractorProvider) {
    instance.inOutInteractor = inOutInteractorProvider.get();
  }
}