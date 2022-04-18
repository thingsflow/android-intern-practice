package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import com.thingsflow.internapplication.data.model.HomeSection
import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.mapper.NovelCoverMapper
import com.thingsflow.internapplication.usecase.GetStoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoryListUseCase: GetStoryListUseCase,
    private val novelCoverMapper: NovelCoverMapper
) : BaseRxViewModel() {

    private val _novelList = MutableLiveData<List<NovelCover>>()
    val novelList : LiveData<List<NovelCover>> = _novelList

    private val _sectionList = MutableLiveData<List<HomeSection>>()
    val sectionList : LiveData<List<HomeSection>> = _sectionList

    fun loadNovelList(){
        getStoryListUseCase.invoke(Unit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onError = {
                    Log.d("LOAD_TEST", "Error: ${it.message}")
                },
                onNext = {
                    _novelList.value = novelCoverMapper.map(it.storyList)

                    val itemList: MutableList<HomeSection> = mutableListOf()

                    for(i in 0..5){
                        itemList.add(i, HomeSection(novelList.value!!, novelList.value!!))
                    }

                    _sectionList.value = itemList
                }
            ).addTo(compositeDisposable)
    }
}