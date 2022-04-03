package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
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

        // github api에서 issue 목록을 가져옴
        mainRepository.getIssues(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _issues.value = ArrayList(it)
                },
                {
                    Log.e("Error: Get issue", "${it.message}")
                }
            )
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