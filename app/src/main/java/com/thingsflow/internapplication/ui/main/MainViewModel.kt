package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var text: String = "Hello World"
    fun updateText(n: Int){
        text = "Hello World $n"
    }
}