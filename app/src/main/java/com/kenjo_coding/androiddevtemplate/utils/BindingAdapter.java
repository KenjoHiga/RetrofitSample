package com.kenjo_coding.androiddevtemplate.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kotlin.jvm.JvmStatic;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter({"imageUrl", "error"})
    @JvmStatic
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.get().load(url).error(error).into(view);
    }
}
