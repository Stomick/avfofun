package evfor.fun.skvader.utils;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import evfor.fun.skvader.R;
import evfor.fun.skvader.utils.callbacks.CallBack;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import static evfor.fun.skvader.utils.UrlUtil.correct;

public class ImageLoader {


    public static void loadYouTubeThamb(String videoId, ImageView view) {
        if (view != null && videoId != null)
            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(getYouTubeThambUrl(videoId), view, standardOptions());
    }

    private static String getYouTubeThambUrl(String videoId) {
        if (videoId.contains("https://"))
            videoId = videoId.substring(videoId.indexOf('=') + 1);
        return "https://img.youtube.com/vi/" + videoId + "/0.jpg";
    }

    public static void loadImage(String url, ImageView view) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_image);
        if (url != null)
        url = correct(url);
//        if (view != null)
//            com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(url, view, standardOptions());
        if (url!=null&&view!=null)
            Glide.with(view.getRootView()).applyDefaultRequestOptions(requestOptions).load(url).into(view);

    }

    public static void loadImage(String url, ImageView view, CallBack callBack) {
        if (url == null)
            return;
        url = correct(url);
        Log.d(ImageLoader.class, String.valueOf(url));
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .displayImage(url, view, standardOptions(), new Listener(callBack));
    }

    public static void loadImageOnMap(String url, ImageView view, CallBack callBack) {
        if (url == null)
            return;
        url = correct(url);
        Log.d(ImageLoader.class, url);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(false)
                .cacheOnDisk(false)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
//        Glide.with(view.getContext())
//                .asBitmap()
//                .load(url)
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//                        resource.compress(Bitmap.CompressFormat.PNG,10,stream);
//
//                        byte[] byteArray = stream.toByteArray();
//                        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
//
//                        view.setImageBitmap(compressedBitmap);
//                        callBack.call();
//                    }
//                });
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .displayImage(url, view, options, new Listener(callBack));
    }

    public static void loadImage(String url, ImageView view, CallBack1<Bitmap> callBack) {
        if (url == null)
            return;
        url = correct(url);
        Log.d(ImageLoader.class, String.valueOf(url));
        com.nostra13.universalimageloader.core.ImageLoader.getInstance()
                .displayImage(url, view, standardOptions(), new ListenerBitmap(callBack));
    }

    public static void loadImage(String url, ImageView view, @DrawableRes int holder) {
        if (url == null)
            return;
        url = correct(url);
        Log.d(ImageLoader.class, String.valueOf(url));
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(url, view, standardOptions(holder), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ((ImageView) view).setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    public static void clearCashUrl(String url) {
        url = correct(url);
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().getDiskCache().get(url).delete();
    }

    private static DisplayImageOptions standardOptions(@DrawableRes int res) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageForEmptyUri(res)
                .showImageOnFail(res)
                .showImageOnLoading(res)
                .build();
    }

    private static DisplayImageOptions standardOptions() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .showImageOnLoading(R.drawable.ic_image)
                .build();
    }


    static class ListenerBitmap implements ImageLoadingListener {

        private CallBack1<Bitmap> callBack;

        public ListenerBitmap(CallBack1<Bitmap> callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            callBack.call(loadedImage);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    }

    static class Listener implements ImageLoadingListener {

        private CallBack callBack;

        Listener(CallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onLoadingStarted(String imageUri, View view) {

        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            ((ImageView) view).setImageBitmap(loadedImage);
            callBack.call();
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {

        }
    }
}
