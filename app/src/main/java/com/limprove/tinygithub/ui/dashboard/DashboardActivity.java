package com.limprove.tinygithub.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.limprove.tinygithub.R;
import com.limprove.tinygithub.databinding.ActivityDashboardBinding;
import com.limprove.tinygithub.ui.dashboard.adapters.RepoAdapter;

public class DashboardActivity extends AppCompatActivity {

    private DashboardViewModel viewModel;
    private RepoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        FirebaseUser user = getIntent().getParcelableExtra("user");
        assert user != null;
        viewModel = new DashboardViewModel(user);
        binding.setViewModel(viewModel);

        adapter = new RepoAdapter();
        binding.setAdapter(adapter);

        viewModel.viewActions.observe(this, viewActions -> {
            FirebaseAuth.getInstance().signOut();
            finish();
        });
    }
}
