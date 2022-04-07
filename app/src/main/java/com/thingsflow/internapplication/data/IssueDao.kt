package com.thingsflow.internapplication.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface IssueDao {
    //@Query("SELECT * FROM issues WHERE organization = :organization and repository = :repository")
    //suspend fun getIssues(organization: String, repository: String): ArrayList<Item.IssueData>?

    @Insert(onConflict = REPLACE)
    suspend fun insertAllIssues(issue: ArrayList<Item.IssueData>)

    @Update
    suspend fun updateIssues(issue: Item.IssueData)

    @Delete
    suspend fun deleteIssues(issue: Item.IssueData)
}