package evfor.fun.skvader.interceptors;

import android.support.annotation.NonNull;

import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.network.URLS;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.header("Content-Type", "application/json");
        if (AuthData.checkToken()) {
            if (chain.request().url().toString().contains(URLS.ChatBase))
                requestBuilder.header("Authorization", AuthData.getToken());
            else
                requestBuilder.header("token", AuthData.getToken());
        }
        return chain.proceed(requestBuilder.build());
    }
}
