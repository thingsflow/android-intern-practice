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
    private val getOnStageNovelCoversUseCase: GetOnStageNovelCoversUseCase
) : BaseRxViewModel() {
    // TODO: Implement the ViewModel
    private val _onStageNovelCovers = MutableLiveData<OnStageNovelCover>()
    val onStageNovelCovers: LiveData<OnStageNovelCover> = _onStageNovelCovers

    fun load() {
        // TODO: Implement
    }
}