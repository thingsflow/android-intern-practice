package com.thingsflow.internapplication.ui.main

import androidx.room.*

//data class Issue(
//    var number: String,
//    var title: String,
//    var body: String = ""
//)

@Entity
data class Issue (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo var number: String,
    @ColumnInfo var title: String,
    @ColumnInfo var body: String = ""
)
