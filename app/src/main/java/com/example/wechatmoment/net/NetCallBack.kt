package com.example.wechatmoment.net

import okhttp3.Request
import okhttp3.Response

open class NetCallBack : MyCallBack {
    override fun onLoadingBefore(request: Request?) {}
    override fun onSuccess(response: String?) {}
    override fun onFailure(request: Request?, e: Exception?) {}
    override fun onError(response: Response?) {}
}