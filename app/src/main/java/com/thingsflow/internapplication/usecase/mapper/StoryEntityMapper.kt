package com.thingsflow.internapplication.usecase.mapper

import com.thingsflow.internapplication.fragment.StoryDto
import com.thingsflow.internapplication.usecase.entities.StoryEntity
import javax.inject.Inject

class StoryEntityMapper @Inject constructor() {

    fun map(from:StoryDto): StoryEntity{
        return StoryEntity(
            id = from.storyId,
            title = from.name,
            mainImgUrl = from.mainImageFile?.link ?:"",
            oneLineDesc = from.oneLineDesc
        )
    }
}