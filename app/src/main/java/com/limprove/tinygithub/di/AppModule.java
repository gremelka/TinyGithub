package com.limprove.tinygithub.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.limprove.tinygithub.data.db.RepoDao;
import com.limprove.tinygithub.domain.repository.RepoRepository;
import com.limprove.tinygithub.data.api.GithubService;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public FirebaseUser provideFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @Provides
    public RepoRepository provideRepoRepository(RepoDao repoDao, GithubService githubService) {
        return new RepoRepository(repoDao, githubService);
    }
}
