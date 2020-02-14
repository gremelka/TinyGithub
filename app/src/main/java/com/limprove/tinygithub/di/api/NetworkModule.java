package com.limprove.tinygithub.di.api;

import com.limprove.tinygithub.data.api.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.limprove.tinygithub.utilities.Constants.BASE_URL;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
