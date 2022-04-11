package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.data.RepositoryInfo
import com.thingsflow.internapplication.data.RepositoryWithIssue
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val issueRepository: IssueRepository,
    private val issueRepositoryRoom: IssueRepositoryRoom,
    private val issueRepositoryCoroutine: IssueRepositoryCoroutine
) : ViewModel() {

    private val BANNER_IMG_URL =
        "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    private val POS = 4

    private val _issueList = MutableLiveData<ArrayList<Item>>()
    val issueList: LiveData<ArrayList<Item>> = _issueList

    private val _repositoryInfo = MutableLiveData<RepositoryInfo>()
    val repositoryInfo: LiveData<RepositoryInfo> = _repositoryInfo

    private val _organization = MutableLiveData<String>()
    val organization: LiveData<String> = _organization

    private val _repository= MutableLiveData<String>()
    val repository: LiveData<String> = _repository

    private val _issueDetail = MutableLiveData<Item.IssueData>()
    val issueDetail: LiveData<Item.IssueData> = _issueDetail

    private val _loadSuccess = MutableLiveData<Boolean>()
    val loadSuccess: LiveData<Boolean> = _loadSuccess

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    private val _eventNavigateToDetail = MutableLiveData<Event<Int>>()
    val eventNavigateToDetail: LiveData<Event<Int>> = _eventNavigateToDetail

    fun setRepositoryInfo(organization: String, repository: String) {
        _repositoryInfo.value = RepositoryInfo(organization, repository)
    }

    private val clearListCh = Channel<Unit>(Channel.CONFLATED)
    private val issuesFlow: Flow<RepositoryWithIssue?> = flowOf(
        clearListCh.receiveAsFlow().map { null },
        repositoryInfo
            .asFlow()
            .flatMapLatest {
                issueRepositoryRoom.getIssueInDatabase(it.organization, it.repository)
            }
    ).flattenMerge(2)

    init {
        viewModelScope.launch {
            issuesFlow.collectLatest {
                Log.d("getIssue", "${it?.organization}/${it?.repository}")
                if(it == null){
                    Log.d("getIssue", "not in db")
                    repositoryInfo.value?.let{ repoInfo ->
                        getIssueFromRemote(repoInfo.organization, repoInfo.repository)
                    }
                }
                else{
                    Log.d("getIssue", "in db setList: ${it.repository}")
                    setIssueList(it.organization, it.repository, it.issueList)
                }
            }
        }
    }

    private fun getIssueFromRemote(organization: String, repository: String){
        viewModelScope.launch {
            try {
                Log.d("getIssue", "get from network")
                val issues = issueRepositoryCoroutine.getIssues(organization, repository).single()
                issueRepositoryRoom.insertIssue(organization, repository, issues)
            } catch (e: Exception) {
                Log.d("getIssue", "load fail from network")
                _loadSuccess.value = false
            }
        }
    }

    private fun setIssueList(organization: String, repository: String, issueList: ArrayList<Item.IssueData>){

        var itemList: ArrayList<Item> = issueList as ArrayList<Item>

        if (itemList.size >= POS) {
            itemList.add(POS, Item.Image(BANNER_IMG_URL))
        }

        _issueList.value = itemList
        setOrganization(organization)
        setRepository(repository)
        _loadSuccess.value = true
    }

    private fun setOrganization(organization: String){
        _organization.value = organization
    }

    private fun setRepository(repository: String){
        _repository.value = repository
    }

//    fun setIssueList(organization: String, repository: String) {
//
//        var itemList: ArrayList<Item> = ArrayList()
//
//        //Rx
//        /*
//        issueRepository.getIssues(organization, repository)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe ({
//                Log.d("getIssue", "success")
//
//                itemList = ArrayList(it)
//
//                if(itemList.size >= POS){
//                    itemList.add(POS, Item.Image(BANNER_IMG_URL))
//                }
//
//                _issueList.value = itemList
//                setRepositoryInfo(organization, repository)
//                _loadSuccess.value = true
//
//            }, {
//                Log.d("getIssue", "fail : ${it.message}")
//                _loadSuccess.value = false
//                _errorMsg.value = it.message
//            })
//        */
//    }

    fun setIssueDetail(index: Int) {
        _issueDetail.value = issueList.value?.get(index) as Item.IssueData?
    }

    fun userClickIssue(index: Int) {
        _eventNavigateToDetail.value = Event(index)
    }
}