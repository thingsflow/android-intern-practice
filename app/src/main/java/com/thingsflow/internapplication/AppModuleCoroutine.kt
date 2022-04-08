package com.thingsflow.internapplication

import android.content.Context
import androidx.room.Room
import com.thingsflow.internapplication.data.IssueDao
import com.thingsflow.internapplication.data.IssueDatabase
import com.thingsflow.internapplication.data.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleCoroutine {

    private const val BASE_URL = "https://api.github.com/repos/"

    @Singleton
    @Provides
    fun provideRetrofitCoroutine(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetroServiceCoroutine(retrofit: Retrofit): GitHubApiCoroutine {
        return retrofit.create(GitHubApiCoroutine::class.java)
    }

    @Singleton
    @Provides
    fun provideIssueDatabase(@ApplicationContext appContext: Context) : IssueDatabase {
        return Room.databaseBuilder(
            appContext,
            IssueDatabase::class.java,
            "issue-db"
        ).build()
    }

    @Provides
    fun provideIssueDao(issueDatabase: IssueDatabase): IssueDao{
        return issueDatabase.issueDao()
    }

    @Provides
    fun provideRepositoryDao(issueDatabase: IssueDatabase): RepositoryDao{
        return issueDatabase.repositoryDao()
    }
}