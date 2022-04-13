package com.thingsflow.internapplication.di.module

import com.thingsflow.internapplication.usecase.GetOnStageNovelCoversUseCase
import com.thingsflow.internapplication.usecase.impl.GetOnStageNovelCoversUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetOnStageNovelCoversUseCase(getOnStageNovelCoversUseCase: GetOnStageNovelCoversUseCaseImpl): GetOnStageNovelCoversUseCase
}