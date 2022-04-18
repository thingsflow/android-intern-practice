package com.thingsflow.internapplication.data.model

data class NovelCover(
    val id: Int,
    val title: String,
    val mainImgUrl: String,
    val oneLineDesc: String,
    val shortDesc: String,
    val isFinished: Boolean
)
