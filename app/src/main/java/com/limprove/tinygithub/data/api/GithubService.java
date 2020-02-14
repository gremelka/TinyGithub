package com.limprove.tinygithub.data.api;

import com.limprove.tinygithub.domain.model.RepoSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

    @GET("search/repositories")
    Call<RepoSearchResponse> searchRepos(@Query("q") String query, @Query("page") Integer page);
}
