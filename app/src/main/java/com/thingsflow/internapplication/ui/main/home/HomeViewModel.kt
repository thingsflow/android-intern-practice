package com.thingsflow.internapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.aac.Event
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.model.SectionItem
import com.thingsflow.internapplication.model.WholeSectionItem
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
    private val _wholeSectionItems = MutableLiveData<ArrayList<WholeSectionItem>>()
    val wholeSectionItems: LiveData<ArrayList<WholeSectionItem>> = _wholeSectionItems

    fun load() {
        _loadingEvent.value = Event(true)
        getOnStageStoriesUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                _loadingEvent.value = Event(false)
            }
            .subscribeBy(
                onError = {
                    Log.e("Error: getOnStageStoriesUseCase", it.message ?: "")
                }, onNext = {
                    val list = ArrayList<WholeSectionItem>()
                    for (i in 0..WHOLE_SECTION_NUM) {
                        list.add(WholeSectionItem(i, SectionItem.TopBannerItem, ArrayList(it.subList(1, TOP_BANNER_STORIES_SIZE + 1))))
                        list.add(WholeSectionItem(i, SectionItem.OnStageStoryItem, ArrayList(it.subList(1, it.size))))
                    }

                    _wholeSectionItems.value = list
                }
            )
    }

    companion object {
        const val TOP_BANNER_STORIES_SIZE = 3
        const val WHOLE_SECTION_NUM = 5
    }
}
