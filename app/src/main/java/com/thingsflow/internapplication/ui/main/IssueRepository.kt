package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject


class IssueRepository @Inject constructor(private val issueDao: IssueDao){
    private val _issueList = MutableLiveData<List<IssueData>>()
    val issueList: LiveData<List<IssueData>> = _issueList

    fun getAll(): LiveData<List<IssueData>> {
        _issueList.value = issueDao.getAll()
        return issueList
    }

    fun insert(issue: IssueData){
        issueDao.insert(issue)
    }

    fun getSize(): Int{
        return issueDao.getSize()
    }

    fun deleteAllIssues(){
        issueDao.deleteAllIssues()
    }

}