package com.limprove.tinygithub.network;

import androidx.lifecycle.LiveData;

import com.limprove.tinygithub.data.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/{login}/repos")
    LiveData<List<Repo>> getRepos(@Path("login") String login);

    @GET("repos/{owner}/{login}")
    LiveData<Repo> getRepo(@Path("owner") String owner, @Path("login") String name);

    @GET("search/repositories")
    Call<List<Repo>> searchRepos(@Query("q") String query);
}
