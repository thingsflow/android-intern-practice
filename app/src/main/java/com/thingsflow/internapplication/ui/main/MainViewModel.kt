package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private lateinit var issues: MutableLiveData<ArrayList<Issue>>
    private var orgName: MutableLiveData<String> = MutableLiveData("google")
    private var repoName: MutableLiveData<String> = MutableLiveData("dagger")

    fun loadIssues(orgName: String, repoName: String) {
        if (issues == null) {
            issues = MutableLiveData(ArrayList())
        }
        // TODO: github api에서 issue 목록 가져오기
    }

    fun getIssues(): LiveData<ArrayList<Issue>> = issues
    fun getOrgName(): LiveData<String> = orgName
    fun getRepoName(): LiveData<String> = repoName

    fun setOrgName(orgName: String) {
        this.orgName.postValue(orgName)
    }

    fun setRepoName(repoName: String) {
        this.repoName.postValue(repoName)
    }

    fun changeTitle(orgName: String, repoName: String) {
        setOrgName(orgName)
        setRepoName(repoName)

        loadIssues(orgName, repoName)
    }
}