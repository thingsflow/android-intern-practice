package com.thingsflow.internapplication.data

import androidx.room.TypeConverter
import com.google.gson.Gson


class IssueTypeConverter {
    @TypeConverter
    fun arrayListToJson(value: ArrayList<Item.IssueData>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToArrayList(value: String) = Gson().fromJson(value, Array<Item.IssueData>::class.java)
        .toList() as ArrayList<Item.IssueData>
}