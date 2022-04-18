package com.thingsflow.internapplication.usecase.impl

import com.thingsflow.internapplication.data.ApiService
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.usecase.GetOnStageStoriesUseCase
import com.thingsflow.internapplication.usecase.mapper.StoryMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetOnStageStoriesUseCaseImpl @Inject constructor(
    private val apiService: ApiService
) : GetOnStageStoriesUseCase {
    override fun invoke(args: Unit): Observable<List<OnStageStory>> {
        return Observable.defer {
            apiService.getStoriesData()
        }
    }
}