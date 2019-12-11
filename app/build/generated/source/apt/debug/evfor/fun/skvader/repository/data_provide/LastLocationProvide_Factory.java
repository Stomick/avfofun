// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package evfor.fun.skvader.repository.data_provide;

import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.internal.Factory;
import evfor.fun.skvader.utils.PermissionController;
import javax.inject.Provider;

public final class LastLocationProvide_Factory implements Factory<LastLocationProvide> {
  private final Provider<FusedLocationProviderClient> fusedLocationClientProvider;

  private final Provider<PermissionController> permissionControllerProvider;

  public LastLocationProvide_Factory(
      Provider<FusedLocationProviderClient> fusedLocationClientProvider,
      Provider<PermissionController> permissionControllerProvider) {
    assert fusedLocationClientProvider != null;
    this.fusedLocationClientProvider = fusedLocationClientProvider;
    assert permissionControllerProvider != null;
    this.permissionControllerProvider = permissionControllerProvider;
  }

  @Override
  public LastLocationProvide get() {
    return new LastLocationProvide(
        fusedLocationClientProvider.get(), permissionControllerProvider.get());
  }

  public static Factory<LastLocationProvide> create(
      Provider<FusedLocationProviderClient> fusedLocationClientProvider,
      Provider<PermissionController> permissionControllerProvider) {
    return new LastLocationProvide_Factory(
        fusedLocationClientProvider, permissionControllerProvider);
  }

  /**
   * Proxies {@link LastLocationProvide#LastLocationProvide(FusedLocationProviderClient,
   * PermissionController)}.
   */
  public static LastLocationProvide newLastLocationProvide(
      FusedLocationProviderClient fusedLocationClient, PermissionController permissionController) {
    return new LastLocationProvide(fusedLocationClient, permissionController);
  }
}