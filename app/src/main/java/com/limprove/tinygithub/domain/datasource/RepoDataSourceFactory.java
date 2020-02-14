package com.limprove.tinygithub.domain.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.limprove.tinygithub.data.api.GithubService;
import com.limprove.tinygithub.domain.model.Repo;

public class RepoDataSourceFactory extends DataSource.Factory<Integer, Repo> {

    private GithubService githubService;
    private String query;

    public final MutableLiveData<RepoDataSource> sourceLiveData = new MutableLiveData<>();

    public RepoDataSourceFactory(GithubService githubService, String query) {
        this.githubService = githubService;
        this.query = query;
    }

    @NonNull
    @Override
    public DataSource<Integer, Repo> create() {
        RepoDataSource dataSource = new RepoDataSource(githubService, query);
        sourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
