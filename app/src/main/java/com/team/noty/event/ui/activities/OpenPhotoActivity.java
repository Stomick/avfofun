package com.team.noty.event.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.marchinram.rxgallery.RxGallery;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.exceptions.PermissionException;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.utils.FilesUtils;
import com.team.noty.event.utils.RealPathUtil;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import butterknife.BindView;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

@SuppressLint("CheckResult")
public class OpenPhotoActivity extends BaseActivity {

    public enum AspectFor {
        LOGO, AVATAR
    }

    private static MaybeEmitter<Uri> emitter;
    private static String FROM_GALLERY = "fg";
    private static String RATIO = "rt";

    public static void setEmitter(MaybeEmitter<Uri> emitter) {
        if (emitter != null)
            OpenPhotoActivity.emitter = emitter;
    }

    public static Maybe<Uri> open(Context context, boolean gallery) {
        context.startActivity(new Intent(context, OpenPhotoActivity.class)
                .putExtra(FROM_GALLERY, gallery));
        return Maybe.create(OpenPhotoActivity::setEmitter);
    }

    public static Maybe<Uri> open(Context context, boolean gallery, AspectFor aspectFor) {
        AspectRatio ratio = null;
        switch (aspectFor) {
            case LOGO:
                ratio = new AspectRatio(16, 9);
                break;
            case AVATAR:
                ratio = new AspectRatio(3, 4);
                break;
        }
        context.startActivity(new Intent(context, OpenPhotoActivity.class)
                .putExtra(FROM_GALLERY, gallery)
                .putExtra(RATIO, ratio));
        return Maybe.create(OpenPhotoActivity::setEmitter);
    }

    @BindView(R.id.cropImageView)
    CropImageView view;

    @Override
    protected int layout() {
        return R.layout.activity_crop;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        view.setOnCropImageCompleteListener((view, result) -> {
            if (result.getError() != null)
                error(result.getError());
            else
                emit(result.getUri());
        });
        readRatio(getIntent().getExtras());
        requestPermissions()
                .subscribe(this::selectImage, this::permissionsDenied);
    }

    private void readRatio(Bundle bundle) {
        AspectRatio ratio;
        if (bundle != null && bundle.containsKey(RATIO) && bundle.getSerializable(RATIO) instanceof AspectRatio) {
            ratio = (AspectRatio) bundle.getSerializable(RATIO);
            if (ratio != null)
                view.setAspectRatio(ratio.x, ratio.y);
        }
    }

    private void permissionsDenied(Throwable throwable) {
        if (throwable instanceof PermissionException)
            DialogProvider.infoOk(this,
                    getString(
                            R.string.permissions_denied_t,
                            ((PermissionException) throwable).getPermissionsDenied()),
                    this::finish)
                    .show();
    }

    private void selectImage() {
        selectImage(getIntent());
    }

    private Completable requestPermissions() {
        return Injector.get().getApp().permissionController().storage();
    }

    private void selectImage(Intent intent) {
        ((checkFromGallery(intent.getExtras()))
                ? RxGallery.gallery(this, false)
                .filter(uris -> !uris.isEmpty())
                .flatMap(uris -> Maybe.just(uris.get(0)))
                .map(this::copyImage)
                : RxGallery.photoCapture(this))
                .subscribe(this::setImage, this::error, this::finish);
    }

    private Uri copyImage(Uri uri) {
        File old = new File(RealPathUtil.getRealPath(this, uri));
        File nw = new File(Environment.getExternalStorageDirectory(), "image_" + System.currentTimeMillis() + ".jpg");
        try {
            FilesUtils.copy(old, nw);
            return Uri.fromFile(nw);
        } catch (IOException e) {
            e.printStackTrace();
            return uri;
        }
    }

    private void setImage(Uri uri) {
        if (view != null)
            view.setImageUriAsync(uri);
        else error(new Throwable(getString(R.string.error)));
    }

    @Override
    protected int menuLayout() {
        return R.menu.crop_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accept:
                accept();
                return true;
            case R.id.rotate:
                rotate();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void rotate() {
        view.rotateImage(-90);
    }

    private void accept() {
        view.saveCroppedImageAsync(view.getImageUri());
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        bar.setTitle(R.string.crop_title);
        setBackArrow(bar);
    }

    private boolean checkFromGallery(Bundle bundle) {
        return bundle != null && bundle.getBoolean(FROM_GALLERY);
    }

    private void error(Throwable t) {
        if (emitter != null && !emitter.isDisposed()) {
            emitter.onError(t);
            finish();
        }
    }

    private void emit(Uri uri) {
        if (emitter != null && !emitter.isDisposed()) {
            emitter.onSuccess(uri);
            emitter.onComplete();
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        emitter = null;
    }

    public static class AspectRatio implements Serializable {
        private int x, y;

        AspectRatio(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
