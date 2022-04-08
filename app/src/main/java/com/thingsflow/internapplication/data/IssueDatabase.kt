package com.thingsflow.internapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        RepositoryWithIssue::class
    ], version = 1
)
@TypeConverters(IssueTypeConverter::class)
abstract class IssueDatabase : RoomDatabase() {
    abstract fun issueDao(): IssueDao
}