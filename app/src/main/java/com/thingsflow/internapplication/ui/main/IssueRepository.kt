package com.thingsflow.internapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.thingsflow.internapplication.GitHubApi
import com.thingsflow.internapplication.data.IssueData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class IssueRepository @Inject constructor(private val retrofitInstance: GitHubApi) {

    fun getIssues(organization: String, repository: String): Observable<ArrayList<IssueData>> {

        return retrofitInstance.getIssue(organization, repository)
    }
}