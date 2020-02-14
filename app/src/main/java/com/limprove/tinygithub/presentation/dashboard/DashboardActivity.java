package com.limprove.tinygithub.presentation.dashboard;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.limprove.tinygithub.R;
import com.limprove.tinygithub.databinding.ActivityDashboardBinding;
import com.limprove.tinygithub.presentation.dashboard.adapters.RepoAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DashboardActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RepoAdapter adapter = new RepoAdapter();
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        DashboardViewModel viewModel = new ViewModelProvider(this, viewModelFactory).get(DashboardViewModel.class);

        binding.setViewModel(viewModel);
        binding.setAdapter(adapter);

        viewModel.viewActions.observe(this, viewActions -> {
            switch (viewActions) {
                case LOGOUT:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                case REFRESH:
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, getString(R.string.update_data), Toast.LENGTH_LONG).show();
            }
        });
        viewModel.getRepos().observe(this, adapter::submitList);
    }
}
