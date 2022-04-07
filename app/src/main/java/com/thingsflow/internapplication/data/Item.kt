package com.thingsflow.internapplication.data

import androidx.room.*
import com.google.gson.annotations.SerializedName

sealed class Item {
    @Entity(tableName = "issues")
    data class IssueData(
        @PrimaryKey
        @SerializedName("number")
        val issueNum: Int,
        @ColumnInfo(name = "title")
        @SerializedName("title")
        val issueTitle: String,
        @ColumnInfo(name = "body")
        @SerializedName("body")
        val issueBody: String,
        @Embedded
        @SerializedName("user")
        val userInfo: UserInfo,
        @Embedded
        val repositoryInfo: RepositoryInfo
    ) : Item()

    data class Image(
        val url: String
    ) : Item()
}

@Entity(tableName = "users")
data class UserInfo(
    @PrimaryKey
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


