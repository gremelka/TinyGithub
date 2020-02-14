package com.limprove.tinygithub.ui.login;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public final class LoginViewModel extends ViewModel {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private MutableLiveData<LoginViewActions> _viewActions = new MutableLiveData<>();
    LiveData<LoginViewActions> viewActions = _viewActions;

    public ObservableField<Boolean> isLoading = new ObservableField<>(false);

    void signIn() {
        _viewActions.postValue(LoginViewActions.SIGN_IN_CLICK);
    }
}
