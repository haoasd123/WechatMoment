package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.wechatmoment.R
import com.example.wechatmoment.bean.ImageBean
import com.example.wechatmoment.util.ImageUtil
import com.example.wechatmoment.util.UIUtils
import com.example.wechatmoment.util.ViewUtils


class ImageHolder : BaseRecyclerHolder {
    var iv_moment_image: ImageView? = null

    companion object {
        fun getInstance(parent: ViewGroup): ImageHolder {
            val convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            return ImageHolder(parent.context, convertView)
        }
    }

    constructor(mContext: Context, itemView: View?) : super(mContext, itemView) {
        iv_moment_image = findViewById(R.id.iv_moment_image)

        ViewUtils.setViewParams(
            iv_moment_image,
            (UIUtils.width -UIUtils.dipToPx(100))/3,
            (UIUtils.width -UIUtils.dipToPx(100))/3
        )
    }

    fun setData(imageBean: ImageBean?) {
        ImageUtil.loadImage(mContext,imageBean?.url,iv_moment_image)
    }
}