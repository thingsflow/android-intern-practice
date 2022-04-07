package com.thingsflow.internapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item.IssueData::class, UserInfo::class, RepositoryInfo::class], version = 1)
abstract class IssueDatabase : RoomDatabase() {
    abstract fun issueDao(): IssueDao
}