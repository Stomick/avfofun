package evfor.fun.skvader.dagger2.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import evfor.fun.skvader.interceptors.LoggingBodyInterceptor;
import evfor.fun.skvader.interceptors.RequestHeaderInterceptor;
import evfor.fun.skvader.network.URLS;
import evfor.fun.skvader.network.api.ChatApi;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.utils.ErrorHandler;
import evfor.fun.skvader.utils.RxCallAdapter;
import evfor.fun.skvader.utils.iHandler;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public interface RetrofitModule {

    @Provides
    @Singleton
    static ContentApi contentApi(Retrofit retrofit) {
        return retrofit.create(ContentApi.class);
    }

    @Provides
    @Singleton
    static ChatApi provideChatApi(Retrofit.Builder builder) {
        return builder.baseUrl(URLS.ChatBase).build().create(ChatApi.class);
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(URLS.BASE).build();
    }

    @Provides
    @Singleton
    static Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, OkHttpClient client, RxCallAdapter adapter) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converterFactory)
                .client(client);
    }

    @Provides
    @Singleton
    static RxCallAdapter provideRxCallAdapter(RxJava2CallAdapterFactory original, iHandler<Throwable> errorHandler) {
        return new RxCallAdapter(original, errorHandler);
    }

    @Provides
    @Singleton
    static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttp() {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(new RequestHeaderInterceptor())
                .addInterceptor(new LoggingBodyInterceptor())
                .build();
    }

//    @Provides
//    @Singleton
//    static Cache provideCache(Context context){
//        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
//        int cacheSize = 10*1024 * 1024;
//        return new Cache(httpCacheDirectory, cacheSize);
//    }

    @Provides
    @Singleton
    static Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Singleton
    @Binds
    iHandler<Throwable> providesErrorHandler(ErrorHandler handler);

}
