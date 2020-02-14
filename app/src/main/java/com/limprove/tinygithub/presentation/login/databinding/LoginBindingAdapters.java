package com.limprove.tinygithub.presentation.login.databinding;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class LoginBindingAdapters {

    @BindingAdapter("viewVisibility")
    public static void setVisibility(View view, Boolean flag) {
        if (flag) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}