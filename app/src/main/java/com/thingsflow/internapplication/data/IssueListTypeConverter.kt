package com.thingsflow.internapplication.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class IssueListTypeConverter {
    @TypeConverter
    fun listToJson(value: List<Item.Issue>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<Item.Issue> =
        Gson().fromJson(value, Array<Item.Issue>::class.java).toList()
}