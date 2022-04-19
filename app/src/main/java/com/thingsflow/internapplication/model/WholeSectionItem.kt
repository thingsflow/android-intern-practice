package com.thingsflow.internapplication.model

sealed class WholeSectionItem (
    open val id: Int
) {
    data class OnStageStoryItem (
        override val id: Int,
        val stories: List<OnStageStory>
    ) : WholeSectionItem(id)

    data class TopBannerItem (
        override val id: Int,
        val stories: List<OnStageStory>
    ) : WholeSectionItem(id)
}