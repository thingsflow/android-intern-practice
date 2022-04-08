package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.GitHubApi
import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class IssueRepository @Inject constructor(private val retrofitInstance: GitHubApi) {

    fun getIssues(organization: String, repository: String): Single<ArrayList<Item.IssueData>> {

        return retrofitInstance.getIssue(organization, repository)
    }
}