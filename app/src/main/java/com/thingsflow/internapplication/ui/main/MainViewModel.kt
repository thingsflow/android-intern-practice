package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.data.IssueData

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var issueList = MutableLiveData<ArrayList<IssueData>>()
    var organization = MutableLiveData<String>()
    var repository = MutableLiveData<String>()

}