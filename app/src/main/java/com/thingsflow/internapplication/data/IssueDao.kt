package com.thingsflow.internapplication.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface IssueDao {

    @Query("SELECT * FROM repository_with_issue WHERE organization = :organization and repository = :repository")
    suspend fun getRepoWithIssues(organization: String, repository: String): RepositoryWithIssue

    @Insert(onConflict = REPLACE)
    suspend fun insertAllIssues(repositoryWithIssue: RepositoryWithIssue)
}

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories WHERE organization = :organization and repository = :repository")
    suspend fun getRepository(organization: String, repository: String): RepositoryInfo

    @Insert(onConflict = REPLACE)
    suspend fun insertRepository(repositoryInfo: RepositoryInfo)

}