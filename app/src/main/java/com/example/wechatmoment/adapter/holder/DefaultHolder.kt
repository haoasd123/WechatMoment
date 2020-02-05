package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wechatmoment.R


class DefaultHolder(mContext: Context, itemView: View?) : BaseRecyclerHolder(mContext, itemView) {
    companion object {
        fun getInstance(parent: ViewGroup): DefaultHolder {
            val convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_default, parent, false)
            return DefaultHolder(parent.context, convertView)
        }
    }
}