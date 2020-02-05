package com.example.wechatmoment.adapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.wechatmoment.adapter.holder.ImageHolder
import com.example.wechatmoment.bean.ImageBean

class ImageAdapter : BaseRecyclerAdapter() {

    override fun viewHolderCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        return ImageHolder.getInstance(parent!!)
    }

    override fun viewHolderBind(holder: RecyclerView.ViewHolder?, position: Int) {
        val imageBean:ImageBean = getItem(position) as ImageBean
        if(holder is ImageHolder){
            holder.setData(imageBean)
        }
    }
}