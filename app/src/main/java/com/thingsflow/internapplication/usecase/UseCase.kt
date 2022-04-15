package com.thingsflow.internapplication.usecase

import com.thingsflow.internapplication.model.OnStageStory
import io.reactivex.rxjava3.core.Observable

interface UseCase<T, A> {
    fun invoke(args: A): Observable<T>
}

interface GetOnStageStoriesUseCase : UseCase<List<OnStageStory>, Unit>
