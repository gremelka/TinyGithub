package com.limprove.tinygithub.presentation.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.limprove.tinygithub.R;
import com.limprove.tinygithub.databinding.ElementRepoFullBinding;
import com.limprove.tinygithub.domain.model.Repo;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ElementRepoFullBinding binding = DataBindingUtil.setContentView(this, R.layout.element_repo_full);
        DetailViewModel viewModel = new ViewModelProvider(this, viewModelFactory).get(DetailViewModel.class);
        binding.setViewModel(viewModel);

        viewModel.repo.set((Repo) getIntent().getSerializableExtra("repo"));
    }
}
