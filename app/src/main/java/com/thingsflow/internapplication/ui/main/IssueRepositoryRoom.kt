package com.thingsflow.internapplication.ui.main

import android.util.Log
import com.thingsflow.internapplication.data.IssueDao
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.data.RepositoryWithIssue
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IssueRepositoryRoom @Inject constructor(
    private val issueDao: IssueDao
) {
    fun getIssueInDatabase(
        organization: String,
        repository: String
    ): Flow<RepositoryWithIssue?> {
        return issueDao.getRepoWithIssues(organization, repository)
    }

    suspend fun insertIssue(
        organization: String,
        repository: String,
        issueList: ArrayList<Item.IssueData>
    ){
        issueDao.insertAllIssues(RepositoryWithIssue(organization, repository, issueList))
        Log.d("getIssue", "inserted issues")
    }
}