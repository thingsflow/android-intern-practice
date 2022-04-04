package com.thingsflow.internapplication.data

import com.google.gson.annotations.SerializedName

data class Issue (
    val user: User,
    val number: Int,
    val title: String,
    val body: String
)

data class User (
    @SerializedName("login")
    val id: String,
    @SerializedName("avatar_url")
    val profileUrl: String
)