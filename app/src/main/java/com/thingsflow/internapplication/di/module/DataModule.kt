package com.thingsflow.internapplication.di.module

import com.thingsflow.internapplication.data.ApiHeaderInterceptor
import com.thingsflow.internapplication.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    companion object {
        const val BASE_URL = "https://dev-api.storyplay.com/graphql"
    }

    @Provides
    @Singleton
    fun provideApiService(apiHeaderInterceptor: ApiHeaderInterceptor): ApiService{
        return ApiService(BASE_URL, apiHeaderInterceptor)
    }
}