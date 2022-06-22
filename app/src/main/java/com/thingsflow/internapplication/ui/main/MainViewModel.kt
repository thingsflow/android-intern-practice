package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val inputList = mutableListOf<String>()
    protected val _vmText = MutableLiveData<List<String>>()
    val vmText: LiveData<List<String>> = _vmText

    private val list = mutableListOf<Issue>()

    private val _issueList = MutableLiveData<List<Issue>>()
    val issueList: LiveData<List<Issue>> = _issueList

    protected val _vmTitle = MutableLiveData<String>()
    val vmTitle: LiveData<String> = _vmTitle

    private val _b = MutableLiveData<Boolean>()
    val b: LiveData<Boolean> = _b

    private val client = OkHttpClient.Builder().build()
    private val moshi = Moshi.Builder().build()
    private val retrofitBuild = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiInterface::class.java)

    init {
        _issueList.value = list
        updateList("google", "dagger")
        updateInput("google", "dagger")
        _vmTitle.value = "${inputList[0]}/${inputList[1]}"
        _b.value = true
    }

    fun getList(): MutableList<Issue>{
        return list
    }

    fun getTitle(): String{
        return vmTitle.value.toString()
    }

    fun getOrg(): String{
        return inputList[0]
    }
    fun getRepo(): String{
        return inputList[1]
    }

    fun changeB(bool:Boolean){
        _b.value = bool
    }

    fun updateInput(org: String, repo: String){
        inputList.clear()
        inputList.add(org)
        inputList.add(repo)
        _vmText.value = inputList
        _vmTitle.value = "${inputList[0]}/${inputList[1]}"
    }

    fun updateList(org: String, repo: String) {

//        ApiInterface.getContent().getIssues(org, repo, "token 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").enqueue(object : Callback<List<Issue>>{
//            override fun onResponse(call: Call<List<Issue>>, response: Response<List<Issue>>) {
//                response?.let {
//                    if(it.isSuccessful){
//                        _b.value = true
//                        list.clear()
//                        for (r in response.body()!!){
//                            if(r.body != null) {
//                                list.add(Issue(number = r.number, title = r.title, body = r.body))
//                            } else list.add(Issue(number = r.number, title = r.title))
//                        }
//                        _issueList.value = list
//                    }
//                    else{
//                        _b.value = false
//                        Log.d("LOG", "Connection Failure : ${it.errorBody()?.string()}")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
//            }
//
//        })
    }
}
