package com.thingsflow.internapplication.ui.main

import android.util.Log
import com.thingsflow.internapplication.data.IssueDao
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.data.RepositoryWithIssue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class IssueRepositoryRoom @Inject constructor(
    private val issueDao: IssueDao,
    private val issueRepositoryCoroutine: IssueRepositoryCoroutine
) {
    suspend fun getIssueInDatabase(
        organization: String,
        repository: String
    ): Flow<ArrayList<Item.IssueData>?> {
        return flow {
            val repoIssue = issueDao.getRepoWithIssues(organization, repository).single()

            if(repoIssue == null){
                Log.d("get issue", "not in db")
                insertIssue(organization, repository)
            }

            val issueInDatabase = issueDao.getRepoWithIssues(organization, repository).single()

            emit(issueInDatabase.issueList)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun insertIssue(
        organization: String,
        repository: String
    ){
        val issues = issueRepositoryCoroutine.getIssues(organization, repository).single()
        issueDao.insertAllIssues(RepositoryWithIssue(organization, repository, issues))
    }
}