package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    // TODO: Implement the ViewModel
    private val _issues = MutableLiveData<ArrayList<Issue>>()
    val issues: LiveData<ArrayList<Issue>> = _issues
    private val _orgName = MutableLiveData<String>()
    val orgName: LiveData<String> = _orgName
    private val _repoName = MutableLiveData<String>()
    val repoName: LiveData<String> = _repoName

    private fun loadIssues(orgName: String, repoName: String) {
        if (_issues.value == null) {
            _issues.value = ArrayList()
        }

        // Test data
        val i: Int = 0
        for (i in 0..10) {
            _issues.value?.add(Issue(User("user", "profile"), i, "test${i}", "body${i}"))
        }

        // TODO: github api에서 issue 목록 가져오기
    }

    fun setOrgName(orgName: String) {
        _orgName.value = orgName
    }

    fun setRepoName(repoName: String) {
        _repoName.value = repoName
    }

    fun changeTitle(orgName: String, repoName: String) {
        setOrgName(orgName)
        setRepoName(repoName)

        loadIssues(orgName, repoName)
    }
}