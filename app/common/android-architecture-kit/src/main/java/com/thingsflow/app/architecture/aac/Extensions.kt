package com.thingsflow.app.architecture.aac

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline runnable: (T) -> Unit) {
    liveData.observe(
        viewLifecycleOwner,
        {
            runnable.invoke(it)
        }
    )
}

inline fun <T> Fragment.event(liveData: LiveData<Event<T>>, crossinline runnable: (T) -> Unit) {
    liveData.observe(
        viewLifecycleOwner,
        EventObserver {
            runnable.invoke(it)
        }
    )
}

inline fun <T> AppCompatActivity.observe(liveData: LiveData<T>, crossinline runnable: (T) -> Unit) {
    liveData.observe(
        this,
        {
            runnable.invoke(it)
        }
    )
}

inline fun <T> AppCompatActivity.event(
    liveData: LiveData<Event<T>>,
    crossinline runnable: (T) -> Unit
) {
    liveData.observe(
        this,
        EventObserver {
            runnable.invoke(it)
        }
    )
}
