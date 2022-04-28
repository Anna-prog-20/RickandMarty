package com.example.rickandmorty.presentation

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions

@Suppress("IMPLICIT_CAST_TO_ANY")
fun ImageView.setCircleImageFromUri(uri: String, placeholder: Int = 0) {
    val glideUrl = if (uri.isEmpty()) placeholder else GlideUrl(uri)

    Glide.with(context)
        .load(glideUrl)
        .placeholder(placeholder)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}