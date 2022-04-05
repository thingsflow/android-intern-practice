package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.RetrofitAPI
import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) {
    fun getIssues(org: String, repo: String) : Single<List<Item.Issue>> {
        return retrofitAPI.getIssues(org, repo)
    }
}