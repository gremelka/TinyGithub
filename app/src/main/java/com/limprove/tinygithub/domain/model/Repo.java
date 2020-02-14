package com.limprove.tinygithub.domain.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "repos")
public class Repo implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("stargazers_count")
    public int stars;

    @SerializedName("forks_count")
    public int forks;

    @SerializedName("created_at")
    public String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repo repo = (Repo) o;
        return id == repo.id &&
                stars == repo.stars &&
                forks == repo.forks &&
                name.equals(repo.name) &&
                date.equals(repo.date) &&
                Objects.equals(description, repo.description) &&
                owner.equals(repo.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stars, forks, date, description, owner);
    }

    @SerializedName("description")
    public String description;

    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    public Owner owner;
}