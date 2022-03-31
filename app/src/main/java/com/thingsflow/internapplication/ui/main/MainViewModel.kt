package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _issues = MutableLiveData<ArrayList<Issue>>()
    val issues: LiveData<ArrayList<Issue>> = _issues
    private val _orgName = MutableLiveData<String>()
    val orgName: LiveData<String> = _orgName
    private val _repoName = MutableLiveData<String>()
    val repoName: LiveData<String> = _repoName

    fun loadIssues(orgName: String, repoName: String) {
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