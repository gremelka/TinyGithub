package com.limprove.tinygithub.di;

import androidx.lifecycle.ViewModel;

import com.limprove.tinygithub.ui.dashboard.DashboardViewModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {
    @Binds
    public abstract ViewModel bindDashboardViewModel(DashboardViewModel dashboardViewModel);
}
