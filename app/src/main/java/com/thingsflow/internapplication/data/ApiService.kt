package com.thingsflow.internapplication.data

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx3.rx
import com.thingsflow.internapplication.StoryListQuery
import com.thingsflow.internapplication.fragment.StoryDto
import com.thingsflow.internapplication.model.OnStageStory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(
    baseUrl: String,
    apiHeaderInterceptor: ApiHeaderInterceptor
) {
    private val apolloClient = ApolloClient.builder()
        .serverUrl(baseUrl)
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(apiHeaderInterceptor)
                .build()
        )
        .build()

    fun getStoriesData(): Observable<List<OnStageStory>> {
        return apolloClient.query(StoryListQuery())
            .rx()
            .doOnNext(::handleError)
            .map { item ->
                val list = mutableListOf<OnStageStory>()

                item.data!!.storyList.list.forEach {
                    list.add(
                        OnStageStory(
                            id = it.fragments.storyDto.storyId,
                            title = it.fragments.storyDto.name,
                            shortDesc = it.fragments.storyDto.shortDesc ?: "",
                            isFinished = it.fragments.storyDto.isFinished,
                            imageUrl = it.fragments.storyDto.mainImageFile?.link ?: "",
                            wideImageUrl = it.fragments.storyDto.wideImageFile?.link ?: ""
                        )
                    )
                }

                list
            }
    }

    private fun handleError(response: Response<*>) {
        if (response.hasErrors()) {
            Log.e("Api service handle error", response.errors?.first()?.message ?: "")
        }
    }
}