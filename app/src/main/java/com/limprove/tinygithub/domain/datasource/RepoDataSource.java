package com.limprove.tinygithub.domain.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.limprove.tinygithub.data.api.GithubService;
import com.limprove.tinygithub.domain.model.Repo;
import com.limprove.tinygithub.domain.model.RepoSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RepoDataSource extends PageKeyedDataSource<Integer, Repo> {

    private static final int firstNumberPage = 1;

    private GithubService githubService;
    private String query;

    public RepoDataSource(GithubService githubService, String query) {
        this.githubService = githubService;
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Repo> callback) {
        Timber.d("Initial RepoDataSource");
        githubService.searchRepos(query, firstNumberPage).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepoSearchResponse> call, @NonNull Response<RepoSearchResponse> response) {
                RepoSearchResponse repoSearchResponse = response.body();
                if (repoSearchResponse != null) {
                    List<Repo> items = repoSearchResponse.getItems();
                    callback.onResult(items, null, 2);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RepoSearchResponse> call, @NonNull Throwable t) {
                Timber.i(t);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Repo> callback) {
        Timber.d("Fetching next page: %s", params.key);
        githubService.searchRepos(query, params.key).enqueue(new Callback<RepoSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<RepoSearchResponse> call, @NonNull Response<RepoSearchResponse> response) {
                RepoSearchResponse repoSearchResponse = response.body();
                if (repoSearchResponse != null) {
                    List<Repo> items = repoSearchResponse.getItems();
                    callback.onResult(items, params.key + 1);
                } else {
                    Timber.d("Error code %s", response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RepoSearchResponse> call, @NonNull Throwable t) {
                Timber.i(t);
            }
        });
    }
}