package com.limprove.tinygithub.presentation.detail.databinding;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.limprove.tinygithub.R;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DetailBindingAdapters {

    @BindingAdapter("setAvatarImage")
    public static void setAvatarImage(ImageView imageView, String url) {
        Glide.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter("formatDate")
    public static void formatDate(TextView view, String date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            LocalDate result = LocalDate.parse(date, formatter);
            view.setText(result.toString());
        } else {
            view.setText(view.getContext().getString(R.string.no_date));
        }
    }
}
