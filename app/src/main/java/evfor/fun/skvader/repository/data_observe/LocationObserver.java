package evfor.fun.skvader.repository.data_observe;

import android.annotation.SuppressLint;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.network.models.request.RqCity;
import evfor.fun.skvader.utils.PermissionController;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class LocationObserver implements DataObserver<Location> {

    private static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    private static final int LOCATION_UPDATE_MIN_TIME = 5000;

    private LocationManager locationManager;
    private LocationListener listener;
    private PermissionController permissionController;
    private Interactor<Observable<City>, RqCity> cityProvider;

    @Inject
    LocationObserver(LocationManager locationManager,
                     PermissionController permissionController,
                     Interactor<Observable<City>, RqCity> cityProvider) {
        this.locationManager = locationManager;
        this.permissionController = permissionController;
        this.cityProvider = cityProvider;
    }

    @Override
    public Observable<Location> observe() {
        return permissionController.myLocation()
                .andThen(location());
    }

    @SuppressLint("MissingPermission")
    private Observable<Location> location() {
        return Observable.<android.location.Location>create(e -> {
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            android.location.Location location = null;
            listener = new LocListener(e);
            if (isGPSEnabled || isNetworkEnabled) {
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, listener);
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                if (isGPSEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, listener);
                    if (location == null)
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
            }

            if (location != null)
                e.onNext(location);
        })
                .map(this::getLatLng).mergeWith(getLocationCity())
                .doOnDispose(this::removeListener);
    }

    private Observable<Location> getLocationCity() {
        return cityProvider.call(new RqCity(AuthData.city))
                .take(1)
                .map(city -> new Location(city.location.latitude, city.location.longitude));
    }

    private Location getLatLng(android.location.Location location) {
        return new Location(location.getLatitude(), location.getLongitude());
    }

    private void removeListener() {
        if (listener != null)
            locationManager.removeUpdates(listener);
    }

    private class LocListener implements LocationListener {

        private ObservableEmitter<android.location.Location> singleOnSubscribe;

        LocListener(ObservableEmitter<android.location.Location> singleOnSubscribe) {
            this.singleOnSubscribe = singleOnSubscribe;
        }

        @Override
        public void onLocationChanged(android.location.Location location) {
            if (location != null && !singleOnSubscribe.isDisposed())
                singleOnSubscribe.onNext(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }
}
