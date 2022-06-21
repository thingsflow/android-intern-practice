package com.thingsflow.internapplication.ui.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.http.Header

interface ApiInterface {
    interface GitHubApi{
        @GET("repos/{org}/{repo}/issues")
        fun getIssues(@Path("org") org: String, @Path("repo") repo: String, @Header("Authorization") accessToken: String): Call<List<Issue>>
    }
    companion object {
        fun getContent(): GitHubApi{
            val client = OkHttpClient.Builder().build()
            val moshi = Moshi.Builder().build()
            val retrofitBuild = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(GitHubApi::class.java)
            return retrofitBuild
        }
    }
}