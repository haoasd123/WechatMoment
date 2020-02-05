package com.example.wechatmoment.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.wechatmoment.R

object ImageUtil {
    fun loadImage(context: Context?, url: String?, target: ImageView?) {
        Glide.with(context).load(url).error(R.drawable.default_img)
            .placeholder(R.drawable.default_img).into(target)
    }
}