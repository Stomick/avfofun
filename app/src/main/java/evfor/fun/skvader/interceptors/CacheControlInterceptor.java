package evfor.fun.skvader.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheControlInterceptor implements Interceptor {

    private int max_age;

    public CacheControlInterceptor(int seconds) {
        this.max_age = seconds;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=" + max_age)
                .build();
    }
}
