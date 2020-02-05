package com.example.wechatmoment.net

import okhttp3.Request
import okhttp3.Response

interface MyCallBack {
    fun onLoadingBefore(request: Request?)
    fun onSuccess(response: String?)
    fun onFailure(request: Request?, e: Exception?)
    fun onError(response: Response?)
}