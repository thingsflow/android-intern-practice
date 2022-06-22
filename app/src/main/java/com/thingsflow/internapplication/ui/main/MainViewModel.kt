package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {
    private val _issueList = MutableLiveData<List<Issue>>()
    val issueList: LiveData<List<Issue>> = _issueList

    private val _listTitle = MutableLiveData<String>()
    val listTitle: LiveData<String> = _listTitle

    private val _searchSuccess = MutableLiveData<Boolean>()
    val searchSuccess: LiveData<Boolean> = _searchSuccess

    init {
        updateList("google", "dagger")
        _searchSuccess.value = true
    }

    fun changeBoolean(bool: Boolean) {
        _searchSuccess.value = bool
    }

    fun updateInput(org: String, repo: String) {
        _listTitle.value = "$org/$repo"
    }

    fun updateList(org: String, repo: String) {
        ApiInterface.getContent().getIssues(org, repo, "token -")
            .enqueue(object : Callback<List<IssueData>> {
                override fun onResponse(
                    call: Call<List<IssueData>>,
                    response: Response<List<IssueData>>
                ) {
                    response.let {
                        if (it.isSuccessful) {
                            _searchSuccess.value = true
                            val list = issueList.value?.toMutableList() ?: mutableListOf()
                            list.clear()
                            for (r in response.body()!!) {
                                if (list.size == 4) {
                                    list.add(
                                        Issue.Image(
                                            "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png",
                                            "https://thingsflow.com/ko/home"
                                        )
                                    )
                                }
                                r.body
                                if(r.body!=null){
                                    list.add(
                                        Issue.GithubIssue(
                                            number = r.number,
                                            title = r.title,
                                            body = r.body
                                        )
                                    )
                                } else{
                                    list.add(
                                        Issue.GithubIssue(
                                            number = r.number,
                                            title = r.title
                                        )
                                    )
                                }
                            }
                            _issueList.value = list
                            updateInput(org, repo)
                        } else {
                            _searchSuccess.value = false
                            Log.d("LOG", "Connection Failure : ${it.errorBody()?.string()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<IssueData>>, t: Throwable) {
                }

            })
    }
}
