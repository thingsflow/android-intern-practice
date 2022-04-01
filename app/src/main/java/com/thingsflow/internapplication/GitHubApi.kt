package com.thingsflow.internapplication

import com.thingsflow.internapplication.data.IssueData
import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("google/dagger/issues")

    fun getIssue(): Call<ArrayList<IssueData>>
}