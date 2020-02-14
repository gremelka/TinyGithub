package com.limprove.tinygithub.ui.dashboard.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardBindingAdapters {

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter) {
        recyclerView.setAdapter(adapter);
    }
}
