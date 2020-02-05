package com.example.wechatmoment.bean

import com.google.gson.annotations.SerializedName

class UserBean {
    @SerializedName("profile-image")
    var profileImage:String? = null
    var avatar:String?=null
    var nick:String?=null
    var username:String?=null
}