package com.limprove.tinygithub.di;

import android.content.Context;

import com.limprove.tinygithub.di.activities.DashboardModule;
import com.limprove.tinygithub.di.activities.DetailModule;
import com.limprove.tinygithub.di.data.DatabaseModule;
import com.limprove.tinygithub.di.api.NetworkModule;
import com.limprove.tinygithub.presentation.GithubApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidSupportInjectionModule.class,
        DatabaseModule.class,
        NetworkModule.class,
        DetailModule.class,
        DashboardModule.class})
public interface AppComponent extends AndroidInjector<GithubApp> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context appContext);
    }
}
