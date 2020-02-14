package com.limprove.tinygithub.di.activities;

import androidx.lifecycle.ViewModel;

import com.limprove.tinygithub.di.ViewModelBuilder;
import com.limprove.tinygithub.di.ViewModelKey;
import com.limprove.tinygithub.presentation.detail.DetailActivity;
import com.limprove.tinygithub.presentation.detail.DetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailModule {

    @ContributesAndroidInjector(modules = {ViewModelBuilder.class})
    abstract DetailActivity detailActivity();

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindViewModel(DetailViewModel viewModel);
}