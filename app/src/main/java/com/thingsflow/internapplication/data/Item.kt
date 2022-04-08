package com.thingsflow.internapplication.data

import androidx.room.*
import com.google.gson.annotations.SerializedName

sealed class Item {
    data class IssueData(
        @SerializedName("number")
        val issueNum: Int,
        @SerializedName("title")
        val issueTitle: String,
        @SerializedName("body")
        val issueBody: String,
        @SerializedName("user")
        val user: UserInfo,
    ) : Item()

    data class Image(
        val url: String
    ) : Item()
}

data class UserInfo(
    @SerializedName("login")
    val userId: String,
    @SerializedName("avatar_url")
    val userProfile: String
)

@Entity(tableName = "repositories", primaryKeys = ["organization", "repository"])
data class RepositoryInfo(
    val organization: String,
    val repository: String
)

@Entity(tableName = "repository_with_issue", primaryKeys = ["organization", "repository"])
data class RepositoryWithIssue(
    val organization: String,
    val repository: String,
    @ColumnInfo(name = "issue_list")
    val issueList: ArrayList<Item.IssueData>
)



