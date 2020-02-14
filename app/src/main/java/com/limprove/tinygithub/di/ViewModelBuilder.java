package com.limprove.tinygithub.di;

import com.limprove.tinygithub.utilities.GithubViewModelFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelBuilder {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);
}
