package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.GitHubApiCoroutine
import com.thingsflow.internapplication.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IssueRepositoryCoroutine @Inject constructor(
    private val retrofitInstance: GitHubApiCoroutine,
    private val issueDao: IssueDao,
    private val repositoryDao: RepositoryDao
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

    suspend fun getRepositoryRoom(
        organization: String,
        repository: String
    ) : RepositoryInfo{
        return repositoryDao.getRepository(organization, repository)
    }

    suspend fun getIssueRoom(
        organization: String,
        repository: String
    ) : RepositoryWithIssue{
        return issueDao.getRepoWithIssues(organization, repository)
    }

    suspend fun insertRepositoryRoom(
        organization: String,
        repository: String
    ) {
        repositoryDao.insertRepository(RepositoryInfo(organization, repository))
    }

    suspend fun insertIssueRoom(
        organization: String,
        repository: String,
        issues: ArrayList<Item.IssueData>
    ) {
        issueDao.insertAllIssues(RepositoryWithIssue(organization, repository, issues))
    }
}