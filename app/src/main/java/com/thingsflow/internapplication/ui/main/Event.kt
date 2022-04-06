package com.thingsflow.internapplication.ui.main

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