package com.thingsflow.internapplication.ui.main

import com.thingsflow.internapplication.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    companion object {
        const val BASE_URL = "https://api.github.com/repos/"
    }

    @Singleton
    @Provides
    fun provideRetrofitApi() : RetrofitAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(retrofitAPI: RetrofitAPI) : MainRepository = MainRepository(retrofitAPI)
}