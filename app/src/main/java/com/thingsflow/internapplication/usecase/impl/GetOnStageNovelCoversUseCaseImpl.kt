package com.thingsflow.internapplication.usecase.impl

import com.thingsflow.internapplication.data.ApiService
import com.thingsflow.internapplication.model.OnStageNovelCover
import com.thingsflow.internapplication.usecase.GetOnStageNovelCoversUseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetOnStageNovelCoversUseCaseImpl @Inject constructor(
    private val apiService: ApiService
) : GetOnStageNovelCoversUseCase {
    override fun invoke(args: Unit): Observable<OnStageNovelCover> {
        TODO("Not yet implemented")
    }
}