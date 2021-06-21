package com.route.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:urlToImage")
fun setImageFromUrl(image:ImageView,url:String){
    Glide.with(image)
        .load(url)
        .into(image)
}