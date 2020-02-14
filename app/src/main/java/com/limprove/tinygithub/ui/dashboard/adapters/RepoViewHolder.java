package com.limprove.tinygithub.ui.dashboard.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.limprove.tinygithub.data.model.Repo;
import com.limprove.tinygithub.databinding.ElementRepoSimpleBinding;

public class RepoViewHolder extends RecyclerView.ViewHolder {

    private ElementRepoSimpleBinding binding;

    public RepoViewHolder(@NonNull ElementRepoSimpleBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
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
