package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.RetrofitAPI
import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
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
}