package com.thingsflow.internapplication.di.module

import com.thingsflow.internapplication.usecase.GetOnStageStoriesUseCase
import com.thingsflow.internapplication.usecase.impl.GetOnStageStoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetOnStageStoriesUseCase(getOnStageStoriesUseCase: GetOnStageStoriesUseCaseImpl): GetOnStageStoriesUseCase
}