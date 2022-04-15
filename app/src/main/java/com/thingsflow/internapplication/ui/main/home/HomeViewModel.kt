package com.thingsflow.internapplication.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.usecase.GetOnStageStoriesUseCase
import com.thingsflow.internapplication.usecase.mapper.StoryMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOnStageStoriesUseCase: GetOnStageStoriesUseCase,
) : BaseRxViewModel() {
    // TODO: Implement the ViewModel
    private val _onStageStoriesByGenre = MutableLiveData<ArrayList<OnStageStory>>()
    val onStageStoriesByGenre: LiveData<ArrayList<OnStageStory>> = _onStageStoriesByGenre

    fun load() {
        // TODO: Implement
//        _onStageStoriesByGenre.value = ArrayList()
//        for (i in 1..10) {
//            _onStageStoriesByGenre.value?.add(OnStageStory(1, ", ", ", ", "", false, ""))
//        }

        getOnStageStoriesUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Log.e("Error: getOnStageStoriesUseCase", it.message ?: "")
                }, onNext = {
                    _onStageStoriesByGenre.value = ArrayList(it)
                }
            )
    }
}