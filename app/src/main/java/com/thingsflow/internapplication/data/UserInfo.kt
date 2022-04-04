package com.thingsflow.internapplication.data

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("login")
    val userId: String,
    @SerializedName("avatar_url")
    val userProfile: String
)
