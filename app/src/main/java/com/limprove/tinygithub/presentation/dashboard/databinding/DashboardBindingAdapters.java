package com.limprove.tinygithub.presentation.dashboard.databinding;

import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class DashboardBindingAdapters {

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("searchViewListener")
    public static void setSearchViewListener(SearchView searchView, SearchView.OnQueryTextListener listener) {
        searchView.setOnQueryTextListener(listener);
    }

    @BindingAdapter("viewVisible")
    public static void setVisibility(View view, boolean flag) {
        if (flag) view.setVisibility(View.VISIBLE);
        else view.setVisibility(View.GONE);
    }

    @BindingAdapter("setTabLayoutListener")
    public static void setTabLayoutListener(TabLayout tabLayout, TabLayout.OnTabSelectedListener listener) {
        tabLayout.addOnTabSelectedListener(listener);
    }
}
