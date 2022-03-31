package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.RetrofitManager
import io.reactivex.rxjava3.core.Single

class MainRepository {
    fun getIssues(org: String, repo: String) : Single<List<Issue>> {
        return RetrofitManager.retrofitApi.getIssues(org, repo)
    }
}