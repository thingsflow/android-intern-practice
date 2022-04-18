package com.thingsflow.internapplication.mapper

import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.usecase.entities.StoryEntity
import javax.inject.Inject

class NovelCoverMapper @Inject constructor() {

    fun map(from: List<StoryEntity>): List<NovelCover>{
        return from.map {
            map(it)
        }
    }

    fun map(from: StoryEntity): NovelCover{
        return NovelCover(
            id = from.id,
            title = from.title,
            mainImgUrl = from.mainImgUrl,
            oneLineDesc = from.oneLineDesc,
            shortDesc = from.shortDesc,
            isFinished = from.isFinished
        )
    }
}