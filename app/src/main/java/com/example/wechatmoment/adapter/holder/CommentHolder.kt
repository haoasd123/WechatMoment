package com.example.wechatmoment.adapter.holder

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wechatmoment.R
import com.example.wechatmoment.bean.CommentBean


class CommentHolder : BaseRecyclerHolder {
    val iv_user_comment: TextView
    val colorSpan = ForegroundColorSpan(Color.BLUE)

    companion object {
        fun getInstance(parent: ViewGroup): CommentHolder {
            val convertView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_comment, parent, false)
            return CommentHolder(parent.context, convertView)
        }
    }

    constructor(mContext: Context, itemView: View?) : super(mContext, itemView) {
        iv_user_comment = findViewById(R.id.iv_user_comment)
    }

    fun setData(commentBean: CommentBean?) {
        val ss = SpannableString(commentBean?.sender?.nick+" : "+commentBean?.content)
        ss.setSpan(colorSpan, 0, commentBean?.sender?.nick?.length!!, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        iv_user_comment.text = ss
    }
}