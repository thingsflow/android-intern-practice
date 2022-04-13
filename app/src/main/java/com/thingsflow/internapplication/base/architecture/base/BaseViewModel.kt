package com.thingsflow.internapplication.base.architecture.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thingsflow.internapplication.base.architecture.aac.Event
import com.thingsflow.internapplication.base.architecture.data.res.StringModel

open class BaseViewModel : ViewModel(), Cleaner {
    protected val _loadingEvent = MutableLiveData<Event<Boolean>>()
    val loadingEvent: LiveData<Event<Boolean>> = _loadingEvent

    protected val _toastEvent = MutableLiveData<Event<StringModel>>()
    val toastEvent: LiveData<Event<StringModel>> = _toastEvent

    override fun onDestroyView() {
        // do nothing
    }
}
