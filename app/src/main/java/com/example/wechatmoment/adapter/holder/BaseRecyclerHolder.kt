package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View

open class BaseRecyclerHolder(var mContext: Context, itemView: View?) :
    ViewHolder(itemView) {
    fun <T : View> findViewById(id: Int): T {
        return itemView.findViewById(id)
    }

}