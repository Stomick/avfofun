package evfor.fun.skvader.dagger2.modules;

import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.network.api.RegistrationApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RegLogModule {

    @Provides
    @Singleton
    public LoginApi provideLoginApi(Retrofit retrofit){
        return retrofit.create(LoginApi.class);
    }

    @Provides
    @Singleton
    public RegistrationApi provideRegistrationApi(Retrofit retrofit){
        return retrofit.create(RegistrationApi.class);
    }
}
