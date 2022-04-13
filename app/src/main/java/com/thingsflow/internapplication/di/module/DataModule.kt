package com.thingsflow.internapplication.di.module

import com.thingsflow.internapplication.data.ApiService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    companion object {
        const val BASE_URL = "https://dev-api.storyplay.com/graphql"
    }

    fun provideApiService(): ApiService{
        return ApiService(BASE_URL)
    }
}