package com.thingsflow.internapplication.ui.main

data class IssueData(
    var number: String,
    var title: String,
    var body: String = ""
)

sealed class Issue(
    open var body: String = ""
) {
    data class GithubIssue(
        var number: String,
        var title: String,
        override var body: String = ""
    ) : Issue(body)

    data class Image(
        val imageLink: String,
        override var body: String = ""
    ) : Issue(body)
}