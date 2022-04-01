package com.thingsflow.internapplication.data

import com.google.gson.annotations.SerializedName

data class IssueData(
    @SerializedName("number")
    val issueNum: Int,
    @SerializedName("title")
    val issueTitle: String
)
