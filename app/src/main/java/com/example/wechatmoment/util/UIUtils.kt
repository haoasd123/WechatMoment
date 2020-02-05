package com.example.wechatmoment.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object UIUtils {
    private var context: Context? = null
    var dM: DisplayMetrics? = null
        private set
    private var realDm: DisplayMetrics? = null
    private var measureHeight = 0
    private var measureWidth = 0

    val density: Float
        get() = dM!!.density

    fun initDisplayMetrics(ctx: Context?, wm: WindowManager) {
        if (context == null) {
            context = ctx
        }
        dM = DisplayMetrics()
        realDm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dM)
        wm.defaultDisplay.getRealMetrics(realDm)
        setMeasureWidth(if (dM!!.widthPixels < dM!!.heightPixels) dM!!.widthPixels else dM!!.heightPixels)
        setMeasureHeight(if (dM!!.widthPixels < dM!!.heightPixels) dM!!.heightPixels else dM!!.widthPixels)
    }

    fun setMeasureHeight(measureHeight: Int) {
        if (measureHeight > 0) {
            UIUtils.measureHeight = measureHeight
        }
    }

    fun setMeasureWidth(measureWidth: Int) {
        if (measureWidth > 0) {
            UIUtils.measureWidth = measureWidth
        }
    }

    fun dipToPx(dip: Int): Int {
        return if (dM == null) {
            -1
        } else (dip * dM!!.density + 0.5f).toInt()
    }

    fun dipToPx(dip: Float): Float {
        return if (dM == null) {
            (-1).toFloat()
        } else dip * dM!!.density + 0.5f
    }

    /**
     * 只能竖屏使用
     *
     * @return
     */
    val width: Int
        get() = if (measureWidth < measureHeight) measureWidth else measureHeight

    val height: Int
        get() = if (measureHeight > measureWidth) measureHeight else measureWidth

    /**
     * 只能竖屏使用
     *
     * @return
     */
    val realWidth: Int
        get() = if (realDm!!.widthPixels < realDm!!.heightPixels) realDm!!.widthPixels else realDm!!.heightPixels

    val realHeight: Int
        get() = if (realDm!!.heightPixels > realDm!!.widthPixels) realDm!!.heightPixels else realDm!!.widthPixels
}