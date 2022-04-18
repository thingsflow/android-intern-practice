package com.thingsflow.internapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.usecase.GetOnStageStoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOnStageStoriesUseCase: GetOnStageStoriesUseCase,
) : BaseRxViewModel() {
    private val _onStageStoriesByGenre = MutableLiveData<ArrayList<OnStageStory>>()
    val onStageStoriesByGenre: LiveData<ArrayList<OnStageStory>> = _onStageStoriesByGenre

    private val _topBannerStories = MutableLiveData<ArrayList<OnStageStory>>()
    val topBannerStories: LiveData<ArrayList<OnStageStory>> = _topBannerStories

    fun load() {
        getOnStageStoriesUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Log.e("Error: getOnStageStoriesUseCase", it.message ?: "")
                }, onNext = {
                    _onStageStoriesByGenre.value = ArrayList(it.subList(1, it.size))
                    _topBannerStories.value = ArrayList(it.subList(1, 1 + TOP_BANNER_STORIES_SIZE))
                }
            )
    }

    companion object {
        const val TOP_BANNER_STORIES_SIZE = 3
    }
}
