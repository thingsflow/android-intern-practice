package com.thingsflow.internapplication.ui.main

import androidx.room.*
@Dao
interface IssueDao {
    @Query("SELECT * FROM Issue_table")
    fun getAll(): List<IssueData>

    @Insert
    fun insert(issue: IssueData)

    @Query("SELECT count(*) FROM Issue_table")
    fun getSize(): Int

    @Query("DELETE FROM Issue_table")
    fun deleteAllIssues()
}