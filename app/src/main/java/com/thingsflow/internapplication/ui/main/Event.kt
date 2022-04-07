package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.Observer

open class Event<T> (value: T) {
    var value = value
        private set

    private var isAlreadyHandled = false

    fun isActive(): T? {
        return if (isAlreadyHandled) {
            null
        } else {
            isAlreadyHandled = true
            value
        }
    }
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.isActive()?.let {
            onEventUnhandledContent(it)
        }
    }
}


