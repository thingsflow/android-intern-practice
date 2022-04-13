package com.thingsflow.internapplication.base.architecture.data.res

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

sealed class ColorModel {

    @ColorInt
    fun get(context: Context): Int {
        return when (this) {
            is ResColor -> context.getColor(resId)
            is StringColor -> Color.parseColor(colorString)
            is IntColor -> intColor
        }
    }
}

data class ResColor(
    @ColorRes val resId: Int
) : ColorModel()

data class StringColor(
    val colorString: String
) : ColorModel()

data class IntColor(
    @ColorInt val intColor: Int
) : ColorModel()
