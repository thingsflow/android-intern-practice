package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.GitHubApiCoroutine
import com.thingsflow.internapplication.data.IssueDao
import com.thingsflow.internapplication.data.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IssueRepositoryCoroutine @Inject constructor(
    private val retrofitInstance: GitHubApiCoroutine,
    private val issueDao: IssueDao
) {
    suspend fun getIssues(
        organization: String,
        repository: String
    ): Flow<ArrayList<Item.IssueData>> {
        return flow {
            val issueList = retrofitInstance.getIssue(organization, repository)
            emit(issueList)
        }.flowOn(Dispatchers.IO)
    }
    /*
    suspend fun getIssuesRoom(
        organization: String,
        repository: String
    ) : ArrayList<Item.IssueData>? {
        return issueDao.getIssues(organization, repository)
    }*/
}