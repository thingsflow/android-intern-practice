package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    protected val _vmText = MutableLiveData<String>()
    val vmText: LiveData<String> = _vmText

    fun initText(){
        _vmText.value = "Hello, World!"
    }
}