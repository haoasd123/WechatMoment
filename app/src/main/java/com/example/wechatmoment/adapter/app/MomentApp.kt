package com.example.wechatmoment.adapter.app

import android.app.Application
import android.content.Context
import android.view.WindowManager
import com.example.wechatmoment.util.UIUtils

class MomentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        UIUtils.initDisplayMetrics(this,
            applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        )
    }
}