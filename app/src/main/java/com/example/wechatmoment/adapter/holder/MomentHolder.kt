package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.wechatmoment.R
import com.example.wechatmoment.adapter.adapter.CommentAdapter
import com.example.wechatmoment.adapter.adapter.ImageAdapter
import com.example.wechatmoment.bean.MomentBean
import com.example.wechatmoment.util.ImageUtil
import com.example.wechatmoment.util.UIUtils


class MomentHolder : BaseRecyclerHolder {
    var iv_user_avatar: ImageView? = null
    var tv_user_username: TextView? = null
    var tv_user_content: TextView? = null
    var rv_moment_images: RecyclerView? = null
    var gridLayoutManager: GridLayoutManager? = null
    val imageAdapter: ImageAdapter

    var rv_moment_comments: RecyclerView? = null
    val linearLayoutManager:LinearLayoutManager
    val commentAdapter:CommentAdapter
    companion object {
        fun getInstance(parent: ViewGroup): MomentHolder {
            val convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_moment, parent, false)
            return MomentHolder(parent.context, convertView)
        }
    }

    constructor(mContext: Context, itemView: View?) : super(mContext, itemView) {
        iv_user_avatar = findViewById(R.id.iv_user_avatar)
        tv_user_username = findViewById(R.id.tv_user_username)
        tv_user_content = findViewById(R.id.tv_user_content)
        rv_moment_images = findViewById(R.id.rv_moment_images)
        imageAdapter = ImageAdapter()
        rv_moment_images?.adapter = imageAdapter
        gridLayoutManager = GridLayoutManager(mContext, 3)
        rv_moment_images?.layoutManager = gridLayoutManager
        rv_moment_images?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                if (parent.layoutManager.getPosition(view) > 2) {
                    outRect.top = UIUtils.dipToPx(5)
                }
            }
        })
        rv_moment_comments = findViewById(R.id.rv_moment_comments)
        linearLayoutManager = LinearLayoutManager(mContext)
        commentAdapter = CommentAdapter()
        rv_moment_comments?.layoutManager = linearLayoutManager
        rv_moment_comments?.adapter = commentAdapter
    }

    fun setData(momentBean: MomentBean?) {
        ImageUtil.loadImage(mContext,momentBean?.sender?.avatar,iv_user_avatar)
        tv_user_username?.text = momentBean?.sender?.nick
        if(TextUtils.isEmpty(momentBean?.content)){
            tv_user_content?.visibility = View.GONE
        }else{
            tv_user_content?.visibility = View.VISIBLE
            tv_user_content?.text = momentBean?.content
        }
        if (momentBean?.images!=null && momentBean?.images?.size != 0) {
            rv_moment_images?.visibility = View.VISIBLE
            imageAdapter.clear()
            imageAdapter.addDataList(momentBean?.images)
            imageAdapter.notifyDataSetChanged()
        } else {
            rv_moment_images?.visibility = View.GONE
        }

        if(momentBean?.comments != null && momentBean?.comments?.size!=0){
            rv_moment_comments?.visibility=View.VISIBLE
            commentAdapter.clear()
            commentAdapter.addDataList(momentBean?.comments)
            commentAdapter.notifyDataSetChanged()
        }else{
            rv_moment_comments?.visibility=View.GONE
        }
    }
}