package com.limprove.tinygithub.ui.dashboard;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

public final class DashboardViewModel extends ViewModel {

    private MutableLiveData<DashboardViewActions> _viewActions = new MutableLiveData<>();
    public LiveData<DashboardViewActions> viewActions = _viewActions;

    private FirebaseUser user;
    public ObservableField<String> accountName = new ObservableField<>();
    public ObservableField<String> accountEmail = new ObservableField<>();

    public DashboardViewModel(FirebaseUser user) {
        this.user = user;
        accountName.set(user.getDisplayName());
        accountEmail.set(user.getEmail());
    }

    public void onLogoutClicked() {
        _viewActions.postValue(DashboardViewActions.LOGOUT);
    }
}

/*public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}*/