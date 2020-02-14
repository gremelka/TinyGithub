package com.limprove.tinygithub.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "repos")
public class Repo {
    @PrimaryKey
    @SerializedName("id")
    public long id;

    @SerializedName("login")
    public String name;

    @SerializedName("stargazers_count")
    public int stars;

    @SerializedName("forks_count")
    public int forks;

    @SerializedName("created_at")
    public long date;

    @SerializedName("description")
    public String description;

    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    public Owner owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Repo)) return false;
        Repo repo = (Repo) o;
        return id == repo.id &&
                stars == repo.stars &&
                forks == repo.forks &&
                date == repo.date &&
                name.equals(repo.name) &&
                description.equals(repo.description) &&
                owner.equals(repo.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stars, forks, date, description, owner);
    }
}