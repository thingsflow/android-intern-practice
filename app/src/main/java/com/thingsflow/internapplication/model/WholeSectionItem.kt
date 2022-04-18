package com.thingsflow.internapplication.model

data class WholeSectionItem (
    val id: Int,
    val onStageStoriesByGenre: ArrayList<OnStageStory>,
    val topBannerStories: ArrayList<OnStageStory>
)