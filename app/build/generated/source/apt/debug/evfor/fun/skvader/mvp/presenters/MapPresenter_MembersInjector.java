// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.mvp.presenters;

import dagger.MembersInjector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.repository.data_provide.DataProvide;
import io.reactivex.Observable;
import javax.inject.Provider;

public final class MapPresenter_MembersInjector implements MembersInjector<MapPresenter> {
  private final Provider<Interactor<Observable<Act>, Location>> actByLocationProvider;

  private final Provider<DataProvide<Location>> lastLocationProvider;

  public MapPresenter_MembersInjector(
      Provider<Interactor<Observable<Act>, Location>> actByLocationProvider,
      Provider<DataProvide<Location>> lastLocationProvider) {
    assert actByLocationProvider != null;
    this.actByLocationProvider = actByLocationProvider;
    assert lastLocationProvider != null;
    this.lastLocationProvider = lastLocationProvider;
  }

  public static MembersInjector<MapPresenter> create(
      Provider<Interactor<Observable<Act>, Location>> actByLocationProvider,
      Provider<DataProvide<Location>> lastLocationProvider) {
    return new MapPresenter_MembersInjector(actByLocationProvider, lastLocationProvider);
  }

  @Override
  public void injectMembers(MapPresenter instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.actByLocation = actByLocationProvider.get();
    instance.lastLocation = lastLocationProvider.get();
  }

  public static void injectActByLocation(
      MapPresenter instance,
      Provider<Interactor<Observable<Act>, Location>> actByLocationProvider) {
    instance.actByLocation = actByLocationProvider.get();
  }

  public static void injectLastLocation(
      MapPresenter instance, Provider<DataProvide<Location>> lastLocationProvider) {
    instance.lastLocation = lastLocationProvider.get();
  }
}