package com.example.wechatmoment.util

import android.view.View
import android.view.ViewGroup.MarginLayoutParams

object ViewUtils {
    fun setViewParams(v: View?, width: Int, height: Int) {
        if (v == null) {
            return
        }
        val params = v.layoutParams as MarginLayoutParams ?: return
        if (width >= 0) params.width = width
        if (height >= 0) params.height = height
        v.layoutParams = params
    }

    /**
     * 设置ViewGroup中子view的大小位置
     */
    fun setViewParams(
        v: View?,
        width: Int,
        height: Int,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {
        if (v == null) {
            return
        }
        val params = v.layoutParams as MarginLayoutParams ?: return
        params.setMargins(left, top, right, bottom) //控件相对父控件左上右下的距离
        if (width >= 0) params.width = width
        if (height >= 0) params.height = height
        v.layoutParams = params
        v.requestLayout()
    }
}