package com.thingsflow.internapplication.model

data class OnStageStory (
    val id: Int,
    val title: String,
    val imageUrl: String,
    val wideImageUrl: String,
    val introImageUrl: String,
    val isFinished: Boolean,
    val shortDesc: String
)