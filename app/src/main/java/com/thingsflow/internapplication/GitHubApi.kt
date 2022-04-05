package com.thingsflow.internapplication

import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.collections.ArrayList

interface GitHubApi {
    @GET("{org}/{repo}/issues")
    fun getIssue(
        @Path("org") org: String,
        @Path("repo") repo: String
    ): Observable<ArrayList<Item.IssueData>>
}