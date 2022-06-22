package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val inputList = mutableListOf<String>()
    protected val _liveInputList = MutableLiveData<List<String>>()
    val liveInputList: LiveData<List<String>> = _liveInputList

    private val list = mutableListOf<Issues>()
    private val _issueList = MutableLiveData<List<Issues>>()
    val issueList: LiveData<List<Issues>> = _issueList

    protected val _listTitle = MutableLiveData<String>()
    val listTitle: LiveData<String> = _listTitle

    private val _searchSuccess = MutableLiveData<Boolean>()
    val searchSuccess: LiveData<Boolean> = _searchSuccess

    init {
        _issueList.value = list
        updateList("google", "dagger")
        updateInput("google", "dagger")
        _listTitle.value = "${inputList[0]}/${inputList[1]}"
        _searchSuccess.value = true
    }

    fun changeBoolean(bool:Boolean){
        _searchSuccess.value = bool
    }

    fun updateInput(org: String, repo: String){
        inputList.clear()
        inputList.add(org)
        inputList.add(repo)
        _liveInputList.value = inputList
        _listTitle.value = "${inputList[0]}/${inputList[1]}"
    }

    fun updateList(org: String, repo: String) {
        ApiInterface.getContent().getIssues(org, repo, "token -").enqueue(object : Callback<List<Issues>>{
            override fun onResponse(call: Call<List<Issues>>, response: Response<List<Issues>>) {
                response?.let {
                    if(it.isSuccessful){
                        _searchSuccess.value = true
                        list.clear()
                        for (r in response.body()!!){
                            if(list.size==4) {
                                list.add(Issues("", "", body = "https://thingsflow.com/ko/home"))
                            }

                            if(r.body != null) {
                                list.add(Issues(number = r.number, title = r.title, body = r.body))
                            } else list.add(Issues(number = r.number, title = r.title))
                        }
                        _issueList.value = list
                        updateInput(org, repo)
                    }
                    else{
                        _searchSuccess.value = false
                        Log.d("LOG", "Connection Failure : ${it.errorBody()?.string()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Issues>>, t: Throwable) {
                Log.d("LOG", "Connection Failure?!")
            }

        })
    }
}
