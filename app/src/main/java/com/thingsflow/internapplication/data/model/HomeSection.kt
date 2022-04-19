package com.thingsflow.internapplication.data.model

sealed class HomeSection{
    data class BannerNovel(
        val bannerNovelList: List<NovelCover>
    ) : HomeSection()

    data class GenreNovel(
        val genreNovelList: List<NovelCover>
    ) : HomeSection()
}
