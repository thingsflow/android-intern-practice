package com.thingsflow.internapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        GithubRepo::class,
        Item.Issue::class,
        User::class
    ],
    version = 1
)
@TypeConverters(IssueListTypeConverter::class)
abstract class GithubRepoDatabase : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}