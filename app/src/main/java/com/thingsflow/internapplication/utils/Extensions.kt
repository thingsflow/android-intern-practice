package com.thingsflow.internapplication.utils

import androidx.fragment.app.Fragment
import com.thingsflow.internapplication.MainActivity

fun Fragment.showLoading() {
    (activity as? MainActivity)?.showProgress()
}

fun Fragment.hideLoading() {
    (activity as? MainActivity)?.hideProgress()
}