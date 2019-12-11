package com.team.noty.event.repository.data_observe;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.team.noty.event.models.Location;
import com.team.noty.event.utils.PermissionController;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class FusedLocationObserver implements DataObserver<Location> {

    private FusedLocationProviderClient fusedLocationClient;
    private PermissionController permissionController;
    private CallBack callBack;

    @Inject
    FusedLocationObserver(FusedLocationProviderClient fusedLocationClient, PermissionController permissionController) {
        this.fusedLocationClient = fusedLocationClient;
        this.permissionController = permissionController;
    }

    @Override
    public Observable<Location> observe() {
        return permissionController.myLocation()
                        .andThen(Observable.create(this::create))
                        .doOnDispose(() -> fusedLocationClient.removeLocationUpdates(callBack))
                        .map(location -> new Location(location.getLatitude(), location.getLongitude()));
    }

    @SuppressLint("MissingPermission")
    private void create(ObservableEmitter<android.location.Location> e) {
        callBack = new CallBack(e);
        fusedLocationClient.requestLocationUpdates(createLocationRequest(), callBack, null);
    }

    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    private class CallBack extends LocationCallback {

        private ObservableEmitter<android.location.Location> emitter;

        public CallBack(ObservableEmitter<android.location.Location> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (!emitter.isDisposed())
                emitter.onNext(locationResult.getLastLocation());
        }
    }
}
