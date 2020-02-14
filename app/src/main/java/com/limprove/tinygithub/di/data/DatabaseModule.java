package com.limprove.tinygithub.di.data;

import android.content.Context;

import androidx.room.Room;

import com.limprove.tinygithub.data.db.GithubRoomDatabase;
import com.limprove.tinygithub.data.db.RepoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Singleton
    @Provides
    GithubRoomDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, GithubRoomDatabase.class, "github.db")
                .build();
    }

    @Provides
    RepoDao provideRepoDao(GithubRoomDatabase database) {
        return database.repoDao();
    }
}
