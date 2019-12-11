package evfor.fun.skvader.ui.utils.reg_step_view;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.interceptors.Interactor;
import evfor.fun.skvader.interceptors.UpdatePhotoInteractor;
import evfor.fun.skvader.models.UpdatePhoto;
import evfor.fun.skvader.utils.callbacks.CallBack;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Completable;

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
