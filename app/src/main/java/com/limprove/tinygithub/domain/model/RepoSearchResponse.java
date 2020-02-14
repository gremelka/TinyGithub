package com.limprove.tinygithub.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    int total;

    @SerializedName("items")
    List<Repo> items;

    public RepoSearchResponse(int total, List<Repo> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }
}
