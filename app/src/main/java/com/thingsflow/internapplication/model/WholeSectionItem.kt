package com.thingsflow.internapplication.model

data class WholeSectionItem (
    val id: Int,
    val type: SectionItem,
    val stories: ArrayList<OnStageStory>
)

enum class SectionItem {
    OnStageStoryItem, TopBannerItem
}