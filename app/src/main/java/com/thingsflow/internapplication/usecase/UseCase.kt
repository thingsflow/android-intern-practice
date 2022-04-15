package com.thingsflow.internapplication.usecase

import com.thingsflow.internapplication.usecase.entities.StoryListEntity
import io.reactivex.rxjava3.core.Observable

interface UseCase<T, A> {
    fun invoke(args: A) : Observable<T>
}

interface GetStoryListUseCase : UseCase<StoryListEntity, Unit>