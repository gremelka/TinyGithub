package com.limprove.tinygithub.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.limprove.tinygithub.data.api.GithubService;
import com.limprove.tinygithub.data.db.RepoDao;
import com.limprove.tinygithub.domain.datasource.RepoDataSourceFactory;
import com.limprove.tinygithub.domain.model.Repo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class RepoRepository {

    private final RepoDao repoDao;
    private final GithubService githubService;

    public PagedList.Config pagedListConfig = new PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(30)
            .setInitialLoadSizeHint(30)
            .build();

    public Executor networkExecutor = Executors.newFixedThreadPool(5);

    @Inject
    public RepoRepository(RepoDao repoDao, GithubService githubService) {
        this.repoDao = repoDao;
        this.githubService = githubService;
    }

    public LiveData<PagedList<Repo>> getReposByQueryFromDb(String query) {
        DataSource.Factory<Integer, Repo> factory  = repoDao.getReposByQuery(query);
        return new LivePagedListBuilder<>(factory, pagedListConfig)
                .build();
    }

    public Completable insertRepo(Repo repo) {
        return repoDao.insertRepo(repo)
                .subscribeOn(Schedulers.io());
    }

    public RepoDataSourceFactory getRepoDataSourceFactory(String query) {
        return new RepoDataSourceFactory(githubService, query);
    }
}