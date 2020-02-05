package com.example.wechatmoment.net

import android.os.Handler
import android.os.Looper
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpUtil private constructor() {
    private val mOkHttpClient: OkHttpClient
    private val mHandler: Handler
    /**
     * 对外提供的Get方法访问
     *
     * @param url
     * @param callBack
     */
    fun Get(url: String, callBack: MyCallBack) {
        /**
         * 通过url和GET方式构建Request
         */
        val request = bulidRequestForGet(url)
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack)
    }

    /**
     * 对外提供的Post方法访问
     *
     * @param url
     * @param parms:   提交内容为表单数据
     * @param callBack
     */
    fun PostWithFormData(
        url: String,
        parms: Map<String, String>?,
        callBack: MyCallBack
    ) {
        /**
         * 通过url和POST方式构建Request
         */
        val request = bulidRequestForPostByForm(url, parms)
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack)
    }

    /**
     * 对外提供的Post方法访问
     *
     * @param url
     * @param json:    提交内容为json数据
     * @param callBack
     */
    fun PostWithJson(url: String, json: String, callBack: MyCallBack) {
        /**
         * 通过url和POST方式构建Request
         */
        val request = bulidRequestForPostByJson(url, json)
        /**
         * 请求网络的逻辑
         */
        requestNetWork(request, callBack)
    }

    /**
     * POST方式构建Request {json}
     *
     * @param url
     * @param json
     * @return
     */
    private fun bulidRequestForPostByJson(
        url: String,
        json: String
    ): Request {
        val body = RequestBody.create(JSON, json)
        return Request.Builder()
            .url(url)
            .post(body)
            .build()
    }

    /**
     * POST方式构建Request {Form}
     *
     * @param url
     * @param parms
     * @return
     */
    private fun bulidRequestForPostByForm(
        url: String,
        parms: Map<String, String>?
    ): Request {
        val builder = FormBody.Builder()
        if (parms != null) {
            for ((key, value) in parms) {
                builder.add(key, value)
            }
        }
        val body = builder.build()
        return Request.Builder()
            .url(url)
            .post(body)
            .build()
    }

    /**
     * GET方式构建Request
     *
     * @param url
     * @return
     */
    private fun bulidRequestForGet(url: String): Request {
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }

    private fun requestNetWork(request: Request, callBack: MyCallBack) {
        var callBack: MyCallBack? = callBack
        if (callBack == null) {
            callBack = NetCallBack()
        }
        val finalCallBack: MyCallBack = callBack
        /**
         * 处理连网逻辑，此处只处理异步操作enqueue
         */
        callBack.onLoadingBefore(request)
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                mHandler.post { finalCallBack.onFailure(request, e) }
            }

            override fun onResponse(
                call: Call,
                response: Response
            ) {
                if (response.isSuccessful) {
                    try {
                        val str = response.body().string()
                        mHandler.post { finalCallBack.onSuccess(str) }
                    } catch (e: Exception) {
                        finalCallBack.onError(response)
                    }
                } else {
                    mHandler.post { finalCallBack.onError(response) }
                }
            }
        })
    }

    companion object {
        @JvmStatic
        var instance: OkHttpUtil? = null
            get() {
                if (field == null) {
                    synchronized(OkHttpUtil::class.java) {
                        if (field == null) {
                            field = OkHttpUtil()
                        }
                    }
                }
                return field
            }
            private set
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    init {
        /**
         * okHttp3中超时方法移植到Builder中
         */
        mOkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        mHandler = Handler(Looper.getMainLooper())
    }
}