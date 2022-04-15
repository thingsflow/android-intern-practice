package com.thingsflow.internapplication.usecase.impl

import com.thingsflow.internapplication.ApiService
import com.thingsflow.internapplication.usecase.GetStoryListUseCase
import com.thingsflow.internapplication.usecase.entities.StoryEntity
import com.thingsflow.internapplication.usecase.entities.StoryListEntity
import com.thingsflow.internapplication.usecase.mapper.StoryEntityMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetStoryListUseCaseImpl @Inject constructor(
    private val apiService: ApiService,
    private val storyEntityMapper: StoryEntityMapper
) : GetStoryListUseCase {
    override fun invoke(args: Unit): Observable<StoryListEntity> {
        return Observable.defer {

            apiService.getStoryData()
        }
            .map {
                val storyList = mutableListOf<StoryEntity>()

                it.storyList.forEach { item ->
                    storyList.add(storyEntityMapper.map(item))
                }

                StoryListEntity(
                    storyList
                )
            }
    }

}