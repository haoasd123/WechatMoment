package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wechatmoment.R
import com.example.wechatmoment.bean.UserBean
import com.example.wechatmoment.util.ImageUtil
import com.example.wechatmoment.util.UIUtils
import com.example.wechatmoment.util.ViewUtils


class UserHolder : BaseRecyclerHolder {
    val iv_user_profile_image: ImageView
    val iv_user_avatar: ImageView
    val tv_user_username: TextView

    companion object {
        fun getInstance(parent: ViewGroup): UserHolder {
            val convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
            return UserHolder(parent.context, convertView)
        }
    }

    constructor(mContext: Context, itemView: View?) : super(mContext, itemView) {
        iv_user_profile_image = findViewById(R.id.iv_user_profile_image)
        iv_user_avatar = findViewById(R.id.iv_user_avatar)
        tv_user_username = findViewById(R.id.tv_user_username)

        ViewUtils.setViewParams(
            iv_user_profile_image,
            UIUtils.width,
            UIUtils.width
        )
        ViewUtils.setViewParams(
            iv_user_avatar,
            UIUtils.dipToPx(80),
            UIUtils.dipToPx(80),
            0,
            UIUtils.width - UIUtils.dipToPx(60),
            0,
            0
        )
    }

    fun setData(userBean: UserBean?) {
        ImageUtil.loadImage(mContext,userBean?.profileImage,iv_user_profile_image)
        ImageUtil.loadImage(mContext,userBean?.avatar,iv_user_avatar)
        tv_user_username.text = userBean?.nick
    }
}