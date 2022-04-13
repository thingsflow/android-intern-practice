package com.thingsflow.internapplication.usecase

import com.thingsflow.internapplication.model.OnStageNovelCover
import io.reactivex.rxjava3.core.Observable

interface UseCase<T, A> {
    fun invoke(args: A): Observable<T>
}

interface GetOnStageNovelCoversUseCase : UseCase<OnStageNovelCover, Unit>
