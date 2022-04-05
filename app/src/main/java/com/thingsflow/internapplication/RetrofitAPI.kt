package com.thingsflow.internapplication

import com.thingsflow.internapplication.data.Item
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {
    @GET("{org}/{repo}/issues")
    fun getIssues(@Path("org") org: String, @Path("repo") repo: String) : Single<List<Item.Issue>>
}