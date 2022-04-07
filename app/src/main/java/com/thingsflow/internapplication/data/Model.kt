package com.thingsflow.internapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User (
    @PrimaryKey
    @SerializedName("login")
    val id: String,
    @SerializedName("avatar_url")
    @ColumnInfo(name = "profile_url")
    val profileUrl: String
)

@Entity(
    tableName = "github_repo",
    primaryKeys = ["org_name", "repo_name"]
)
data class GithubRepo (
    @ColumnInfo(name = "org_name")
    val orgName: String,
    @ColumnInfo(name = "repo_name")
    val repoName: String,
    @ColumnInfo(name = "issue_list")
    val issueList: List<Item.Issue>
)