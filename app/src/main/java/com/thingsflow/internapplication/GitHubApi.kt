package com.thingsflow.internapplication

import com.thingsflow.internapplication.data.IssueData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import kotlin.collections.ArrayList

interface GitHubApi {
    @GET("google/dagger/issues")

    fun getIssue(): Observable<ArrayList<IssueData>>
}