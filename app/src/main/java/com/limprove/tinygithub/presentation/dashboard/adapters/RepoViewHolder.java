package com.limprove.tinygithub.presentation.dashboard.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.limprove.tinygithub.databinding.ElementRepoSimpleBinding;
import com.limprove.tinygithub.domain.model.Repo;
import com.limprove.tinygithub.presentation.detail.DetailActivity;

public class RepoViewHolder extends RecyclerView.ViewHolder {

    private ElementRepoSimpleBinding binding;

    public RepoViewHolder(@NonNull ElementRepoSimpleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("repo", binding.getRepo());
            v.getContext().startActivity(intent);
        });
    }

    public static RepoViewHolder create(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ElementRepoSimpleBinding binding = ElementRepoSimpleBinding.inflate(inflater, parent, false);
        return new RepoViewHolder(binding);
    }

    void bind(Repo item) {
        binding.setRepo(item);
        binding.executePendingBindings();
    }
}
