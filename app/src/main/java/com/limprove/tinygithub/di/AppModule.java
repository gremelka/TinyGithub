package com.limprove.tinygithub.di;

import android.app.Application;

import com.limprove.tinygithub.data.db.GithubDatabase;
import com.limprove.tinygithub.data.db.RepoDao;
import com.limprove.tinygithub.network.GithubService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return application;
    }

    @Singleton
    @Provides
    public GithubService provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);
    }

    @Singleton
    @Provides
    GithubDatabase provideDatabase(Application application) {
        return GithubDatabase.getInstance(application);
    }

    @Singleton
    @Provides
    RepoDao provideRepoDao(GithubDatabase database) {
        return database.repoDao();
    }
}
