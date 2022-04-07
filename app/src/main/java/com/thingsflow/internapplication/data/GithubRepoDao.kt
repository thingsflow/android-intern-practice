package com.thingsflow.internapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GithubRepoDao {
    @Insert
    fun insert(githubRepo: GithubRepo)

    @Query("SELECT * FROM github_repo WHERE org_name = :orgName and repo_name = :repoName")
    fun getGithubRepoByOrgAndRepo(orgName: String, repoName: String) : GithubRepo
}