package com.thingsflow.internapplication.module

import com.thingsflow.internapplication.usecase.GetStoryListUseCase
import com.thingsflow.internapplication.usecase.impl.GetStoryListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetStoryListUseCase(getStoryListUseCase: GetStoryListUseCaseImpl): GetStoryListUseCase
}