package com.limprove.tinygithub.data.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.limprove.tinygithub.domain.model.Repo;

import io.reactivex.Completable;

@Dao
public interface RepoDao {

    @Query("SELECT * FROM repos WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'")
    DataSource.Factory<Integer, Repo> getReposByQuery(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRepo(Repo repo);
}
