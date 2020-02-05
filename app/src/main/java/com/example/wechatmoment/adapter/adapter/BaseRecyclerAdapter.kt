package com.example.wechatmoment.adapter.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import java.util.*

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<ViewHolder>(),
    AdapterInterface {
     val TAG = javaClass.simpleName
     var mData: MutableList<Any?>
    abstract fun viewHolderCreate(parent: ViewGroup?, viewType: Int): ViewHolder?
    abstract fun viewHolderBind(holder: ViewHolder?, position: Int)

    init {
        mData = ArrayList()
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        return viewHolderCreate(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        viewHolderBind(holder, position)
    }

    override fun onBindViewHolder(
        holder: ViewHolder?,
        position: Int,
        payloads: List<*>?
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun setData(data: List<Any?>?) {
        mData.clear()
        if (null == data || data.size == 0) {
            return
        }
        mData.addAll(data)
    }

    override fun getData(): List<Any?>? {
        return mData
    }

    fun getItem(position: Int): Any? {
        return if (null != getData() && position >= 0 && position < getData()!!.size) {
            mData[position]
        } else null
    }

    override fun addData(data: Any?) {
        if (null == data) {
            return
        }
        mData.add(data)
    }

    override fun addDataList(data: List<Any?>?) {
        if (null == data || data.size == 0) {
            return
        }
        mData.addAll(data)
    }

    override fun clear() {
        mData.clear()
    }

    override fun updateChange() {
        notifyDataSetChanged()
    }

    /**
     * 局部刷新
     *
     * @param position
     */
    fun updateChange(position: Int) {
        this.notifyItemChanged(position)
    }

    override fun removeItem(position: Int) {
        if (position >= mData.size) {
            return
        }
        mData.removeAt(position)
    }
}