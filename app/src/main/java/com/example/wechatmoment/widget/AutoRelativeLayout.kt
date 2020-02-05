package com.example.wechatmoment.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

class AutoRelativeLayout : RelativeLayout {
    var recyclerView: RecyclerView? = null
    private var downY = 0f
    private var canDownScroll = true
    private var canUpScroll = true
    var onRefreshListener: OnRefreshListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            downY = ev.rawY
        }
        if (ev.action == MotionEvent.ACTION_MOVE) {
            val moveY = ev.rawY
            if (recyclerView != null) {
                if (!recyclerView!!.canScrollVertically(1)) {
                    if (moveY - downY < 0) {
                        recyclerView!!.translationY = (moveY - downY)/2
                        if (onRefreshListener != null && canDownScroll) {
                            onRefreshListener!!.onLoadMore()
                        }
                        canDownScroll = false
                    }
                    if (!canDownScroll) {
                        return true
                    }
                } else if (!recyclerView!!.canScrollVertically(-1)) {
                    if (moveY - downY > 0) {
                        recyclerView!!.translationY = (moveY - downY)/2
                        canUpScroll = false
                    }
                    if (!canUpScroll) {
                        return true
                    }
                } else {
                    downY = ev.rawY
                }
            }
        }
        if (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_CANCEL) {
            if (recyclerView != null) {
                recyclerView!!.translationY = 0f
            }
            if (onRefreshListener != null && !canUpScroll) {
                onRefreshListener!!.onRefresh()
            }
            canDownScroll = true
            canUpScroll = true
        }
        return super.dispatchTouchEvent(ev)
    }

    interface OnRefreshListener {
        fun onRefresh()
        fun onLoadMore()
    }
}