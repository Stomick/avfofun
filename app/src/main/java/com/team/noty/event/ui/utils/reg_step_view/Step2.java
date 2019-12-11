package com.team.noty.event.ui.utils.reg_step_view;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.interceptors.Interactor;
import com.team.noty.event.interceptors.UpdatePhotoInteractor;
import com.team.noty.event.models.UpdatePhoto;
import com.team.noty.event.models.User;
import com.team.noty.event.network.api.MainApi;
import com.team.noty.event.repository.LocalRepository;
import com.team.noty.event.utils.RequestMapUtil;
import com.team.noty.event.utils.callbacks.CallBack;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Step2 extends BaseStep {

    @BindView(R.id.reg_step2_imageView)
    ImageView imageView;
    private String path;
    private CallBack openPhoto;

    @Inject
    Interactor<Completable, UpdatePhoto> updatePhoto;

    public Step2() {
        Injector.get().getMain().inject(this);
    }

    @Override
    public void setView(View view) {
        super.setView(view);
        imageView.setOnClickListener(this::openPhoto);
        if (path != null)
            imageView.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    @SuppressLint("CheckResult")
    public void setPath(String path) {
        this.path = path;

        ((UpdatePhotoInteractor)updatePhoto).editPhoto(new UpdatePhoto(path));

//        api.editProfile(RequestMapUtil.uploadFile(path))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(model->Complete(),throwable -> Error());
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    public void setOpenPhoto(CallBack openPhoto) {
        this.openPhoto = openPhoto;
    }

    private void openPhoto(View v) {
        if (openPhoto != null)
            openPhoto.call();
    }

    public String getPath() {
        return path;
    }
    public void Complete()
    {
        Log.e("My","ok");
    }
    public void Error()
    {
        Log.e("My","error");
    }
}
