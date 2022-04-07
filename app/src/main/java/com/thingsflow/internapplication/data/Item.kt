package com.thingsflow.internapplication.data

import androidx.room.*

sealed class Item {
    @Entity(tableName = "issue")
    data class Issue (
        @ColumnInfo(name = "org_name")
        val orgName: String,
        @ColumnInfo(name = "repo_name")
        val repoName: String,
        @Embedded
        val user: User,
        @PrimaryKey
        val number: Int,
        val title: String,
        val body: String
    ) : Item()

    data class Image (
        val url: String
    ) : Item()
}