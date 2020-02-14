package com.limprove.tinygithub.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.limprove.tinygithub.domain.model.Repo;

@Database(entities = {Repo.class}, version = 1, exportSchema = false)
public abstract class GithubRoomDatabase extends RoomDatabase implements GithubDatabase{}