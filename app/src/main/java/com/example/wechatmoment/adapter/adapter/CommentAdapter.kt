package com.example.wechatmoment.adapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.wechatmoment.adapter.holder.*
import com.example.wechatmoment.bean.CommentBean

class CommentAdapter : BaseRecyclerAdapter() {

    override fun viewHolderCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return CommentHolder.getInstance(parent!!)
    }

    override fun viewHolderBind(holder: RecyclerView.ViewHolder?, position: Int) {
        val commentBean:CommentBean = getItem(position) as CommentBean
        if(holder is CommentHolder){
            holder.setData(commentBean)
        }
    }
}