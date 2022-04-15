package com.thingsflow.internapplication.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import com.thingsflow.internapplication.model.OnStageNovelCover
import com.thingsflow.internapplication.usecase.GetOnStageNovelCoversUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val getOnStageNovelCoversUseCase: GetOnStageNovelCoversUseCase
) : BaseRxViewModel() {
    // TODO: Implement the ViewModel
    private val _onStageNovelCoversByGenre = MutableLiveData<ArrayList<OnStageNovelCover>>()
    val onStageNovelCoversByGenre: LiveData<ArrayList<OnStageNovelCover>> = _onStageNovelCoversByGenre

    fun load() {
        // TODO: Implement
        _onStageNovelCoversByGenre.value = ArrayList()
        for (i in 1..10) {
            _onStageNovelCoversByGenre.value?.add(OnStageNovelCover(1, ", ", ", ", false, "aa"))
        }
    }
}