package com.example.wechatmoment.bean

import android.text.TextUtils

class MomentBean {
    var content: String? = null
    var images: List<ImageBean>? = null
    var sender: UserBean? = null
    var comments: List<CommentBean>? = null

    fun isLegalMoment(): Boolean {
        if (sender == null) {
            return false
        }
        return true
    }

}