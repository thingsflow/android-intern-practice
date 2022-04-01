package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.data.IssueData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val issueRepository: IssueRepository): ViewModel() {
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

//        //test data
//        for (i in 1..10){
//            _issueList.value?.add(IssueData(i, "the title of the issue"))
//        }
        issueRepository.getIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Log.d("getIssue", "success")
                _issueList.value = ArrayList(it)
            }, {
                Log.d("getIssue", "fail : ${it.message}")
            })
    }
}