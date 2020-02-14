package com.limprove.tinygithub.presentation.detail;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.limprove.tinygithub.domain.model.Repo;
import com.limprove.tinygithub.domain.repository.RepoRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DetailViewModel extends ViewModel {

    private RepoRepository repository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public ObservableField<Repo> repo = new ObservableField<>();

    @Inject
    public DetailViewModel(RepoRepository repository) {
        this.repository = repository;
    }

    public void insertRepo() {
        Disposable insert = repository.insertRepo(repo.get())
                .subscribe();
        disposable.add(insert);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
