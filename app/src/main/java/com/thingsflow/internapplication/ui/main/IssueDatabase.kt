package com.thingsflow.internapplication.ui.main

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [IssueData::class] , version = 1)
abstract class IssueDatabase : RoomDatabase(){
    abstract fun getIssueDao(): IssueDao
}