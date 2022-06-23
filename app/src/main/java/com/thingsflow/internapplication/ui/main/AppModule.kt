package com.thingsflow.internapplication.ui.main

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        IssueDatabase::class.java,
        "IssueDB"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideIssueDao(db: IssueDatabase) = db.getIssueDao()
}