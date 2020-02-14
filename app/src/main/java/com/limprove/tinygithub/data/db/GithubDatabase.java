package com.limprove.tinygithub.data.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.limprove.tinygithub.data.model.Repo;

@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class GithubDatabase extends RoomDatabase {

    private static volatile GithubDatabase instance;

    public abstract RepoDao repoDao();

    public static GithubDatabase getInstance(Application application) {
        if (instance == null) {
            synchronized (GithubDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application, GithubDatabase.class, "github.db")
                            .build();
                }
            }
        }
        return instance;
    }
}