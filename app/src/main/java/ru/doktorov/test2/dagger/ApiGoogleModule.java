package ru.doktorov.test2.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.doktorov.test2.network.GoogleApi;

@Module(includes = {RetrofitModule.class})
public class ApiGoogleModule {
    @Provides
    @Singleton
    public GoogleApi provideGoogleApi(Retrofit retrofit) {
        return retrofit.create(GoogleApi.class);
    }
}