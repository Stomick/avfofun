package com.team.noty.event.utils;

import android.Manifest;
import android.content.Context;

import com.gun0912.tedpermission.TedPermissionResult;
import com.team.noty.event.R;
import com.team.noty.event.exceptions.PermissionException;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import io.reactivex.Completable;

public class PermissionController {

    private Context context;
    private String[] location = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private String[] record_audio = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] accounts = {Manifest.permission.GET_ACCOUNTS};
    private String[] storage = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    public PermissionController(Context context) {
        this.context = context;
    }

    public Completable myLocation() {
        return TedRx2Permission.with(context)
                .setRationaleTitle(R.string.need_permission)
                .setRationaleMessage(R.string.need_permission_location)
                .setPermissions(location)
                .request()
                .flatMapCompletable(this::fromTedResponse);
    }

    public Completable recordAudio(){
        return TedRx2Permission.with(context)
                .setRationaleTitle(R.string.need_permission)
                .setRationaleMessage(R.string.need_permission_record)
                .setPermissions(record_audio)
                .request()
                .flatMapCompletable(this::fromTedResponse);
    }

    public Completable storage() {
        return TedRx2Permission.with(context)
                .setRationaleTitle(R.string.need_permission)
                .setRationaleMessage(R.string.need_permission_write)
                .setPermissions(storage)
                .request()
                .flatMapCompletable(this::fromTedResponse);
    }

    public Completable accounts(){
       return TedRx2Permission.with(context)
                .setRationaleTitle(R.string.need_permission)
                .setRationaleMessage(R.string.need_permission_account)
                .setPermissions(accounts)
                .request()
                .flatMapCompletable(this::fromTedResponse);
    }

    private Completable fromTedResponse(final TedPermissionResult result) {
        if (result.isGranted())
            return Completable.complete();
        else return Completable.error(new PermissionException(result.getDeniedPermissions()));
    }
}
