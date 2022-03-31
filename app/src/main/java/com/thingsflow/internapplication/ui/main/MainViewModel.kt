package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.data.IssueData

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _issueList = MutableLiveData<ArrayList<IssueData>>()
    val issueList : LiveData<ArrayList<IssueData>> = _issueList

    private val _organization = MutableLiveData<String>()
    val organization : LiveData<String> = _organization

    private val _repository = MutableLiveData<String>()
    val repository : LiveData<String> = _repository

}