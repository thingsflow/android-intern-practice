package com.thingsflow.internapplication.data

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    val id: String,
    @SerializedName("avatar_url")
    val profileUrl: String
)