package com.limprove.tinygithub.data.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.limprove.tinygithub.data.model.Repo;
import com.limprove.tinygithub.network.GithubService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class RepoDataSource extends PageKeyedDataSource<Long, Repo> {

    @Inject
    GithubService githubService;

    String query;

    public RepoDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Repo> callback) {
        Call<List<Repo>> repos = githubService.searchRepos(query);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repo> callback) {

    }
}

/*
* public class PhotoDataSource extends PageKeyedDataSource<Long,Photos> {

    GetDataService dataService;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Photos> callback) {

        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Photos>> data = dataService.getAllPhotos(1);
        data.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                List<Photos> photosList = response.body();
                callback.onResult(photosList,null,(long)2);
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Photos> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Photos> callback) {


        dataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Photos>> data = dataService.getAllPhotos(params.key);
        data.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                List<Photos> photosList = response.body();
                callback.onResult(photosList, String.valueOf(Long.parseLong(paramss.key)+1));
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });


    }
}
*
* */