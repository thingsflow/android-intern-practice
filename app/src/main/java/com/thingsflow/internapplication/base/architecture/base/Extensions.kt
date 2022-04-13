package com.thingsflow.internapplication.base.architecture.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog

fun Context.displayWidth(): Int {
    val display = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        display
    } else {
        (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    }
    val size = Point()
    display?.getRealSize(size)
    return size.x
}

fun Float.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        context.resources.displayMetrics
    ).toInt()
}

fun Activity.fullScreen(statusBarIconWhite: Boolean?): Pair<Int, Int> {
    return window.fullScreen(statusBarIconWhite)
}

fun Activity.restoreScreen(originalFlags: Pair<Int, Int>) {
    with(window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            setDecorFitsSystemWindows(true)
        } else {
            decorView.systemUiVisibility = originalFlags.first
        }
        statusBarColor = originalFlags.second
    }
}

fun Activity.setStatusBarTheme(whiteStatusIcon: Boolean) {
    with(window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            insetsController?.setSystemBarsAppearance(
                if (whiteStatusIcon) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            val currentBlack =
                decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            if (currentBlack && whiteStatusIcon) {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility - View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else if (!currentBlack && !whiteStatusIcon) {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                return
            }
        }
    }
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
    return result
}

fun Context.getNavigationBarHeight(): Int {
    val hasNavigationBarId = resources.getIdentifier("config_showNavigationBar", "bool", "android")

    var result = 0
    if (hasNavigationBarId > 0 && resources.getBoolean(hasNavigationBarId)) {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun AppCompatDialog.fullScreen(statusBarIconWhite: Boolean?): Pair<Int, Int> {
    window?.let {
        return it.fullScreen(statusBarIconWhite)
    } ?: run {
        return Pair(0, Color.TRANSPARENT)
    }
}

fun Window.fullScreen(statusBarIconWhite: Boolean?): Pair<Int, Int> {
    var oriSystemUiFlags = 0
    var oriStatusBarColor: Int = Color.TRANSPARENT

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setDecorFitsSystemWindows(false)
        if (statusBarIconWhite == false) {
            insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    } else {
        oriSystemUiFlags = decorView.systemUiVisibility
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        if (statusBarIconWhite == false) {
            decorView.systemUiVisibility =
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    oriStatusBarColor = statusBarColor
    statusBarColor = Color.TRANSPARENT

    return Pair(oriSystemUiFlags, oriStatusBarColor)
}
