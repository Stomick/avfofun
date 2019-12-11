package evfor.fun.skvader.interceptors;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

import static evfor.fun.skvader.utils.UrlUtil.correct;

public class LoadImageInteractor implements Interactor<Single<Bitmap>, String> {

    @Inject
    public LoadImageInteractor() {
    }

    @Override
    public Single<Bitmap> call(String s) {
        return loadImage(s);
    }

    private Single<Bitmap> loadImage(String url) {
        if (url == null)
            return Single.never();
        url = correct(url);
        return Single.create(new SubscribeBitmap(url));
    }

    static class SubscribeBitmap implements SingleOnSubscribe<Bitmap> {
        String url;

        SubscribeBitmap(String url) {
            this.url = url;
        }

        @Override
        public void subscribe(SingleEmitter<Bitmap> e) {
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().loadImage(url, new BitmapEmiter(e));
        }
    }

    static class BitmapEmiter implements ImageLoadingListener {
        private SingleEmitter<Bitmap> emitter;

        BitmapEmiter(SingleEmitter<Bitmap> emitter) {
            this.emitter = emitter;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            if (!emitter.isDisposed())
                emitter.onError(failReason.getCause());
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (!emitter.isDisposed())
                emitter.onSuccess(loadedImage);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
        }
    }
}
