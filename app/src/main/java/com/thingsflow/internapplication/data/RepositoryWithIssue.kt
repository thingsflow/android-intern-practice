package com.thingsflow.internapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "repository_with_issue", primaryKeys = ["organization", "repository"])
data class RepositoryWithIssue(
    val organization: String,
    val repository: String,
    @ColumnInfo(name = "issue_list")
    val issueList: ArrayList<Item.IssueData>
)