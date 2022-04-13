package com.thingsflow.internapplication.base.architecture.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseRxViewModel : BaseViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
