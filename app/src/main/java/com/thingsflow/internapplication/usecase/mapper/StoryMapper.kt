package com.thingsflow.internapplication.usecase.mapper

import com.thingsflow.internapplication.fragment.StoryDto
import com.thingsflow.internapplication.model.OnStageStory
import javax.inject.Inject

class StoryMapper @Inject constructor() {
    fun map(from: StoryDto): OnStageStory {
        return OnStageStory(
            id = from.storyId,
            title = from.name,
            imageUrl = from.mainImageFile?.link ?: "",
            wideImageUrl = from.wideImageFile?.link ?: "",
            introImageUrl = from.introImageFile?.link ?: "",
            isFinished = from.isFinished,
            shortDesc = from.shortDesc ?: ""
        )
    }
}