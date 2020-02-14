package com.limprove.tinygithub.presentation.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.limprove.tinygithub.R;
import com.limprove.tinygithub.databinding.ActivityLoginBinding;
import com.limprove.tinygithub.presentation.dashboard.DashboardActivity;

import static com.limprove.tinygithub.utilities.Constants.RC_SIGN_IN;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private GoogleSignInClient googleSignInClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);

        prepareSignInButton();
        createAuthClient();

        viewModel.viewActions.observe(this, viewActions -> {
            switch (viewActions) {
                case SIGN_IN_CLICK:
                    Intent intent = googleSignInClient.getSignInIntent();
                    startActivityForResult(intent, RC_SIGN_IN);
                case REVOKE_ACCESS:
                    revokeAccess();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                updateUI(null);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        viewModel.isLoading.set(true);
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        viewModel.firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = viewModel.firebaseAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        updateUI(null);
                    }
                    viewModel.isLoading.set(false);
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    public void revokeAccess() {
        googleSignInClient.revokeAccess();
    }

    // Prepared functions
    private void createAuthClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    // Cannot use databinding for signInButton android:onClick attribute
    // See more: https://stackoverflow.com/questions/51842041/google-signinbuttons-onclick-doesnt-work-using-databinding
    private void prepareSignInButton() {
        final SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(v -> viewModel.signIn());
    }
}