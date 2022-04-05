package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.GitHubApi
import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class IssueRepository @Inject constructor(private val retrofitInstance: GitHubApi) {

    fun getIssues(organization: String, repository: String): Observable<ArrayList<Item.IssueData>> {

        return retrofitInstance.getIssue(organization, repository)
    }
}