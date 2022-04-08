package com.thingsflow.internapplication.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface IssueDao {

    @Query("SELECT * FROM repository_with_issue WHERE organization = :organization and repository = :repository")
    fun getRepoWithIssues(organization: String, repository: String): Flow<RepositoryWithIssue>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllIssues(repositoryWithIssue: RepositoryWithIssue)
}