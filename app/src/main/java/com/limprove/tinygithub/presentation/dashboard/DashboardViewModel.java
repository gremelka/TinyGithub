package com.limprove.tinygithub.presentation.dashboard;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseUser;
import com.limprove.tinygithub.domain.datasource.RepoDataSourceFactory;
import com.limprove.tinygithub.domain.model.Repo;
import com.limprove.tinygithub.domain.repository.RepoRepository;
import com.limprove.tinygithub.utilities.DoubleTrigger;

import javax.inject.Inject;

public final class DashboardViewModel extends ViewModel {

    private MutableLiveData<DashboardViewActions> _viewActions = new MutableLiveData<>();
    LiveData<DashboardViewActions> viewActions = _viewActions;

    private RepoRepository repository;

    private MutableLiveData<String> searchQuery = new MutableLiveData<>("");
    private MutableLiveData<Boolean> isLoadFromApi = new MutableLiveData<>(true);

    public ObservableField<String> accountName = new ObservableField<>();
    public ObservableField<String> accountEmail = new ObservableField<>();

    public ObservableBoolean isLoading = new ObservableBoolean(false);

    private RepoDataSourceFactory factory;

    @Inject
    public DashboardViewModel(FirebaseUser user, RepoRepository repository) {
        this.repository = repository;
        accountName.set(user.getDisplayName());
        accountEmail.set(user.getEmail());
    }

    LiveData<PagedList<Repo>> getRepos() {
        return Transformations.switchMap(new DoubleTrigger(searchQuery, isLoadFromApi), input -> {
            if (!input.second) {
                return repository.getReposByQueryFromDb(input.first);
            } else {
                factory = repository.getRepoDataSourceFactory(input.first);
                return new LivePagedListBuilder<>(factory, repository.pagedListConfig)
                        .setFetchExecutor(repository.networkExecutor)
                        .build();
            }
        });
    }

    public void onLogoutClicked() {
        _viewActions.postValue(DashboardViewActions.LOGOUT);
    }

    public void onRefreshClicked() {
        _viewActions.postValue(DashboardViewActions.REFRESH);
    }

    public SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (query != null && !query.trim().equals("")) {
                searchQuery.postValue(query);
            }
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return true;
        }
    };

    public TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = tab.getPosition();
            if (position == 1) isLoadFromApi.postValue(false);
            else isLoadFromApi.postValue(true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };
}