package com.limprove.tinygithub.ui;

import android.app.Application;

import com.limprove.tinygithub.BuildConfig;
import com.limprove.tinygithub.di.AppComponent;
import com.limprove.tinygithub.di.DaggerAppComponent;

import timber.log.Timber;

public class GithubApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                //.appModule(new AppModule(this))
                .build();
    }
}
