package com.thingsflow.internapplication.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    @ActivityContext private val context: Context,
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
                    // TODO: 새로운 org/repo 입력 시 잘 가져와서 issue에도 잘 넣는데 livedata 가 ui를 업데이트하지 않음
                    Log.d("SUCCESS: Get issue", it[0].title)
                    _issues.value = ArrayList(it)

                    setOrgName(orgName)
                    setRepoName(repoName)
                },
                {
                    Log.e("Error: Get issue", "${it.message}")
                    // TODO: 오류 팝업 창 띄우기
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
        loadIssues(orgName, repoName)
    }
}