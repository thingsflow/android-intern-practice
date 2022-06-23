package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IssueRepository) : ViewModel() {
    private val _issueList = MutableLiveData<List<IssueData>>()
    val issueList: LiveData<List<IssueData>> = _issueList

    private val _listTitle = MutableLiveData<String>()
    val listTitle: LiveData<String> = _listTitle

    private val _searchSuccess = MutableLiveData<Boolean>()
    val searchSuccess: LiveData<Boolean> = _searchSuccess

    init {
        if(repository.getSize()<1||repository.getAll().value==null){
            updateList("google", "dagger")
        }
        else{
            _issueList.value = repository.getAll().value
            var splitedList = issueList.value?.get(1)?.url?.split("/")
            splitedList?.let{
                updateInput(it[4], it[5])
            }
        }
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
                            repository.deleteAllIssues()
                            for (r in response.body()!!) {
                                if ( repository.getSize() == 4) {
                                    repository.insert(
                                        IssueData(
                                            id = repository.getSize(),
                                            url = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png",
                                            body = "https://thingsflow.com/ko/home"
                                        )
                                    )
                                }
                                if(r.body!=null){
                                    repository.insert(
                                        IssueData(
                                            id =repository.getSize(),
                                            url = r.url,
                                            number = r.number,
                                            title = r.title,
                                            body = r.body
                                        )
                                    )
                                } else{
                                    repository.insert(
                                        IssueData(
                                            id = repository.getSize(),
                                            url = r.url,
                                            number = r.number,
                                            title = r.title
                                        )
                                    )
                                }
                            }
                            _issueList.value = repository.getAll().value
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
