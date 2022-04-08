package com.thingsflow.internapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity

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