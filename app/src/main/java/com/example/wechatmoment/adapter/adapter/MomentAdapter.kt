package com.example.wechatmoment.adapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.wechatmoment.adapter.holder.MomentHolder
import com.example.wechatmoment.adapter.holder.DefaultHolder
import com.example.wechatmoment.adapter.holder.UserHolder
import com.example.wechatmoment.bean.ItemDataBean
import com.example.wechatmoment.bean.MomentBean
import com.example.wechatmoment.bean.UserBean

class MomentAdapter : BaseRecyclerAdapter() {
    companion object{
        val TYPE_USER = 1
        val TYPE_CONTENT = 2
    }

    override fun getItemViewType(position: Int): Int {
        val itemDataBean:ItemDataBean = getItem(position) as ItemDataBean
        return itemDataBean.type
    }
    override fun viewHolderCreate(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        if (viewType == TYPE_USER) {
            return UserHolder.getInstance(parent!!)
        }
        if (viewType == TYPE_CONTENT) {
            return MomentHolder.getInstance(parent!!)
        }
        return DefaultHolder.getInstance(parent!!)
    }

    override fun viewHolderBind(holder: RecyclerView.ViewHolder?, position: Int) {
        val itemDataBean:ItemDataBean = getItem(position) as ItemDataBean
        if(holder is UserHolder){
            holder.setData(itemDataBean.data as UserBean)
        }
        if(holder is MomentHolder){
            holder.setData(itemDataBean.data as MomentBean)
        }
    }
}