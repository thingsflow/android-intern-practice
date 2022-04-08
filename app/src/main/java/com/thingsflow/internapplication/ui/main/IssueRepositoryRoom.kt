package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.data.IssueDao
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.data.RepositoryWithIssue
import javax.inject.Inject

class IssueRepositoryRoom @Inject constructor(
    private val issueDao: IssueDao
){
    suspend fun getIssueRoom(
        organization: String,
        repository: String
    ) : RepositoryWithIssue {
        return issueDao.getRepoWithIssues(organization, repository)
    }

    suspend fun insertIssueRoom(
        organization: String,
        repository: String,
        issues: ArrayList<Item.IssueData>
    ) {
        issueDao.insertAllIssues(RepositoryWithIssue(organization, repository, issues))
    }
}