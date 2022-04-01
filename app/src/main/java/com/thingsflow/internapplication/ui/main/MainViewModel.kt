package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.data.IssueData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    // TODO: Implement the ViewModel

    private val _issueList = MutableLiveData<ArrayList<IssueData>>()
    val issueList : LiveData<ArrayList<IssueData>> = _issueList

    private val _organization = MutableLiveData<String>()
    val organization : LiveData<String> = _organization

    private val _repository = MutableLiveData<String>()
    val repository : LiveData<String> = _repository

    fun setOrganization(organization: String){
        _organization.value = organization
    }

    fun setRepository(repository: String){
        _repository.value = repository
    }

    fun setIssueList(){

        _issueList.value = ArrayList()

        //test data
        for (i in 1..10){
            _issueList.value?.add(IssueData(i, "the title of the issue"))
        }
    }
}