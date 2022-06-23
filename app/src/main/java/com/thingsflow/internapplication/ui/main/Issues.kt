package com.thingsflow.internapplication.ui.main

import androidx.room.*

@Entity(tableName = "Issue_table")
data class IssueData(
    @PrimaryKey
    var id: Int,
    var url: String = "",
    var number: String = "",
    var title: String = "",
    var body: String = ""
)

sealed class Issue(open var url: String,
) {
    data class GithubIssue(
        override var url: String,
        var number: String,
        var title: String,
        var body: String = ""
    ) : Issue(url)

    data class Image(
        override var url: String = "",
        val imageLink: String,
        val body: String
    ) : Issue(url)
}

