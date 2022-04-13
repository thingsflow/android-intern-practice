package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.base.architecture.base.BaseRxViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseRxViewModel() {

    private val _testText = MutableLiveData<String>()
    val testText : LiveData<String> = _testText

    fun setTestText(text: String){
        _testText.value = text
    }
}