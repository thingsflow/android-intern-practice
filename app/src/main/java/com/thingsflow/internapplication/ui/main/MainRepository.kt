package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.RetrofitAPI
import com.thingsflow.internapplication.data.GithubRepo
import com.thingsflow.internapplication.data.GithubRepoDatabase
import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI,
    private val githubRepoDatabase: GithubRepoDatabase
) {
    fun getIssuesRx(org: String, repo: String) : Single<List<Item.Issue>> {
        return retrofitAPI.getIssuesRx(org, repo)
    }

    fun getIssuesFlow(org: String, repo: String) : Flow<List<Item.Issue>> {
        return flow {
            val issues = retrofitAPI.getIssuesFlow(org, repo)
            emit(issues)
        }
    }

    fun getIssuesRoom(org: String, repo: String) : Flow<List<Item.Issue>?>  {
        return flow {
            val githubRepo = githubRepoDatabase.githubRepoDao().getGithubRepoByOrgAndRepo(org, repo)
            if (githubRepo != null) emit(githubRepo.issueList)
            else emit(null)
        }
    }

    fun insertGithubRepoToRoom(org: String, repo: String, issueList: List<Item.Issue>) {
        githubRepoDatabase.githubRepoDao().insert(GithubRepo(org, repo, issueList))
    }
}