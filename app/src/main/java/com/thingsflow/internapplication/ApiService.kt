package com.thingsflow.internapplication

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx3.rx
import com.thingsflow.internapplication.data.dto.StoryListDto
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    apiHeaderInterceptor: ApiHeaderInterceptor
){

    private val baseUrl = "https://dev-api.storyplay.com/graphql"

    private val apolloClient = ApolloClient.builder()
        .serverUrl(baseUrl)
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(apiHeaderInterceptor)
                .build()
        )
        .build()

    fun getStoryData(): Observable<StoryListDto> {
        return apolloClient.query(StoryListQuery()).rx()
            .map { response ->
                StoryListDto(
                    storyList = response.data!!.storyList.list.map { it.fragments.storyDto }
                )
            }
    }
}