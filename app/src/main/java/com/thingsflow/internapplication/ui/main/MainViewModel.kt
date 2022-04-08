package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.data.RepositoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val issueRepository: IssueRepository,
    private val issueRepositoryRoom: IssueRepositoryRoom
) : ViewModel() {

    private val BANNER_IMG_URL =
        "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    private val POS = 4

    private val _issueList = MutableLiveData<ArrayList<Item>>()
    val issueList: LiveData<ArrayList<Item>> = _issueList

    private val _repositoryInfo = MutableLiveData<RepositoryInfo>()
    val repositoryInfo: LiveData<RepositoryInfo> = _repositoryInfo

    private val _issueDetail = MutableLiveData<Item.IssueData>()
    val issueDetail: LiveData<Item.IssueData> = _issueDetail

    private val _loadSuccess = MutableLiveData<Boolean>()
    val loadSuccess: LiveData<Boolean> = _loadSuccess

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _eventNavigateToDetail = MutableLiveData<Event<Int>>()
    val eventNavigateToDetail: LiveData<Event<Int>> = _eventNavigateToDetail

    private fun setRepositoryInfo(organization: String, repository: String) {
        _repositoryInfo.value = RepositoryInfo(organization, repository)
    }

    fun setIssueList(organization: String, repository: String) {

        var itemList: ArrayList<Item>

        //Rx
        /*
        issueRepository.getIssues(organization, repository)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Log.d("getIssue", "success")

                itemList = ArrayList(it)

                if(itemList.size >= POS){
                    itemList.add(POS, Item.Image(BANNER_IMG_URL))
                }

                _issueList.value = itemList
                setRepositoryInfo(organization, repository)
                _loadSuccess.value = true

            }, {
                Log.d("getIssue", "fail : ${it.message}")
                _loadSuccess.value = false
                _errorMsg.value = it.message
            })
        */

        //Coroutine
        viewModelScope.launch {
            try {
                val issue =
                    issueRepositoryRoom.getIssueInDatabase(organization, repository).single()

                itemList = ArrayList(issue)

                if (itemList.size >= POS) {
                    itemList.add(POS, Item.Image(BANNER_IMG_URL))
                }

                _issueList.value = itemList
                setRepositoryInfo(organization, repository)
                _loadSuccess.value = true

            } catch (e: Exception) {
                Log.d("getIssue", "load fail")
                _loadSuccess.value = false
            }

        }
    }

    fun setIssueDetail(index: Int) {
        _issueDetail.value = issueList.value?.get(index) as Item.IssueData?
    }

    fun userClickIssue(index: Int) {
        _eventNavigateToDetail.value = Event(index)
    }
}