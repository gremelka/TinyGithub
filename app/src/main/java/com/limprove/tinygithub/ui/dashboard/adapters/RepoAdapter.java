package com.limprove.tinygithub.ui.dashboard.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.limprove.tinygithub.data.model.Repo;

public class RepoAdapter extends PagedListAdapter<Repo, RepoViewHolder> {
    private static final DiffUtil.ItemCallback<Repo> REPO_COMPARATOR = new DiffUtil.ItemCallback<Repo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RepoAdapter() {
        super(REPO_COMPARATOR);
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RepoViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repo item = getItem(position);
        if (item != null) holder.bind(item);
    }
}
