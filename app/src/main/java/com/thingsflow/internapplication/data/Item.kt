package com.thingsflow.internapplication.data

import com.google.gson.annotations.SerializedName

sealed class Item{
    data class IssueData(
        @SerializedName("number")
        val issueNum: Int,
        @SerializedName("title")
        val issueTitle: String,
        @SerializedName("body")
        val issueBody: String,
        @SerializedName("user")
        val userInfo: UserInfo
    ): Item()

    data class Image(
        val url: String
    ): Item()
}
