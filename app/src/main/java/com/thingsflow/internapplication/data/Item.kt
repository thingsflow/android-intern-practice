package com.thingsflow.internapplication.data

sealed class Item {
    data class Issue (
        val user: User,
        val number: Int,
        val title: String,
        val body: String
    ) : Item()

    data class Image (
        val url: String
    ) : Item()
}