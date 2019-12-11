package evfor.fun.skvader.repository.data_provide;

import android.annotation.SuppressLint;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.utils.PermissionController;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.schedulers.Schedulers;

public class LastLocationProvide implements DataProvide<Location> {

    private FusedLocationProviderClient fusedLocationClient;
    private PermissionController permissionController;

    @Inject
    LastLocationProvide(FusedLocationProviderClient fusedLocationClient, PermissionController permissionController) {
        this.fusedLocationClient = fusedLocationClient;
        this.permissionController = permissionController;
    }

    @Override
    public Single<Location> provide() {
        return permissionController.myLocation()
                .subscribeOn(Schedulers.io())
                .andThen(Single.create(SucessListener::new))
                .map(location -> new Location(39, 50));
    }

    private class SucessListener implements OnSuccessListener<android.location.Location>{

        SingleEmitter<android.location.Location> emitter;

        @SuppressLint("MissingPermission")
        SucessListener(SingleEmitter<android.location.Location> emitter) {
            this.emitter = emitter;
            fusedLocationClient.getLastLocation().addOnSuccessListener(this);
        }

        @Override
        public void onSuccess(android.location.Location location) {
            if (location==null)
                location = new android.location.Location("GPS");
            if(!emitter.isDisposed())
                try {
                    emitter.onSuccess(location);
                }catch (Exception e){

                }
        }
    }
}
