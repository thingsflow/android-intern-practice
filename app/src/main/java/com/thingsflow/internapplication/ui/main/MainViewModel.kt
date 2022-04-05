package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.data.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _issues = MutableLiveData<ArrayList<Item>>()
    val issues: LiveData<ArrayList<Item>> = _issues
    private val _orgName = MutableLiveData<String>()
    val orgName: LiveData<String> = _orgName
    private val _repoName = MutableLiveData<String>()
    val repoName: LiveData<String> = _repoName
    private val _loadingError = MutableLiveData<Boolean>()
    val loadingError: LiveData<Boolean> = _loadingError

    companion object {
        const val POS_BANNER = 4
        const val URL_BANNER = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    }

    private fun loadIssues(orgName: String, repoName: String) {
        // github api에서 issue 목록을 가져옴
        mainRepository.getIssues(orgName, repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("SUCCESS: Get issue", "${it.size}")

                    val list: MutableList<Item> = it.toMutableList()
                    if (list.size >= POS_BANNER) {
                        list.add(POS_BANNER, Item.Image(URL_BANNER))
                    }
                    _issues.value = ArrayList(list)

                    setOrgName(orgName)
                    setRepoName(repoName)
                    _loadingError.value = false
                },
                {
                    Log.e("ERROR: Get issue", "${it.message}")
                    _loadingError.value = true
                }
            )
    }

    private fun setOrgName(orgName: String) {
        _orgName.value = orgName
    }

    private fun setRepoName(repoName: String) {
        _repoName.value = repoName
    }

    fun changeTitle(orgName: String, repoName: String) {
        loadIssues(orgName, repoName)
    }
}