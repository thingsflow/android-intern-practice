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
class MainViewModel @Inject constructor(private val issueRepository: IssueRepository): ViewModel() {
    // TODO: Implement the ViewModel
    private val BANNER_IMG_URL = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    private val POS = 4

    private val _issueList = MutableLiveData<ArrayList<Item>>()
    val issueList : LiveData<ArrayList<Item>> = _issueList

    private val _organization = MutableLiveData<String>()
    val organization : LiveData<String> = _organization

    private val _repository = MutableLiveData<String>()
    val repository : LiveData<String> = _repository

    private val _issueDetail = MutableLiveData<Item.IssueData>()
    val issueDetail : LiveData<Item.IssueData> = _issueDetail

    private val _loadSuccess = MutableLiveData<Boolean>()
    val loadSuccess : LiveData<Boolean> = _loadSuccess

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg : LiveData<String> = _errorMsg


    fun setOrganization(organization: String){
        _organization.value = organization
    }

    fun setRepository(repository: String){
        _repository.value = repository
    }

    fun setIssueList(organization: String, repository: String){

        var itemList: ArrayList<Item>

        setOrganization(organization)
        setRepository(repository)

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
                _loadSuccess.value = true
            }, {
                Log.d("getIssue", "fail : ${it.message}")
                _loadSuccess.value = false
                _errorMsg.value = it.message
            })


    }

    fun setIssueDetail(index: Int){
        _issueDetail.value = issueList.value?.get(index) as Item.IssueData?
    }
}