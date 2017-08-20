package ru.doktorov.test2.dagger;

import android.util.Log;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.doktorov.test2.app.Constants;
import ru.doktorov.test2.network.GoogleApi;

@Module
public class RetrofitModule {
    private static final String BASE_URL = "BASE_URL";

    @Provides
    @Named(BASE_URL)
    String provideBaseUrlString() {
        return Constants.URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(BASE_URL) String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message ->
                Log.d("Test Application 2", message)).setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = httpClient.addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    GoogleApi provideGoogleApi(Retrofit retrofit) {
        return retrofit.create(GoogleApi.class);
    }
}

