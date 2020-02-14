package com.limprove.tinygithub.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    int total;

    @SerializedName("items")
    List<Repo> items;
}
