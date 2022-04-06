package com.thingsflow.internapplication

import com.thingsflow.internapplication.data.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiCoroutine {
    @GET("{org}/{repo}/issues")
    suspend fun getIssue(
        @Path("org") org: String,
        @Path("repo") repo: String
    ): ArrayList<Item.IssueData>
}