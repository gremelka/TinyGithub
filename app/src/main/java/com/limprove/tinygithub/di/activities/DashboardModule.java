package com.limprove.tinygithub.di.activities;

import androidx.lifecycle.ViewModel;

import com.limprove.tinygithub.di.ViewModelBuilder;
import com.limprove.tinygithub.di.ViewModelKey;
import com.limprove.tinygithub.presentation.dashboard.DashboardActivity;
import com.limprove.tinygithub.presentation.dashboard.DashboardViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class DashboardModule {

    @ContributesAndroidInjector(modules = {ViewModelBuilder.class})
    abstract DashboardActivity dashboardActivity();

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel.class)
    abstract ViewModel bindViewModel(DashboardViewModel viewModel);
}
