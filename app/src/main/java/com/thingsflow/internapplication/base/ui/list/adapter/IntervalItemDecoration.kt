package com.thingsflow.internapplication.base.ui.list.adapter

import android.graphics.Rect
import android.view.View
import androidx.annotation.IntRange
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class IntervalItemDecoration(
    @IntRange(from = 0) var offset: Int = 0,
    @IntRange(from = 0) var startOffset: Int = 0,
    @IntRange(from = 0) var endOffset: Int = 0,
    @IntRange(from = 0) var gridSideOffset: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        val lastPosition = max(0, (parent.adapter?.itemCount ?: 0) - 1)
        val layoutManager = parent.layoutManager

        if (layoutManager !is LinearLayoutManager) {
            throw NotSupportedLayoutManager()
        }

        val orientation = layoutManager.orientation

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.top = offset / 2
            outRect.bottom = offset / 2
        } else {
            outRect.left = offset / 2
            outRect.right = offset / 2
        }

        if (layoutManager is GridLayoutManager) {
            val spanCount = layoutManager.spanCount
            if (itemPosition % spanCount == 0) {
                outRect.left = gridSideOffset
            }

            if (itemPosition % spanCount == spanCount - 1) {
                outRect.right = gridSideOffset
            }

            if (itemPosition < spanCount) {
                outRect.top = startOffset
            }

            val lastRow = lastPosition - (lastPosition % spanCount)
            if (itemPosition >= lastRow) {
                outRect.bottom = endOffset
            }
        } else {
            if (itemPosition == 0) {
                if (orientation == LinearLayoutManager.VERTICAL) {
                    outRect.top = startOffset
                } else {
                    outRect.left = startOffset
                }
            }

            if (itemPosition == lastPosition) {
                if (orientation == LinearLayoutManager.VERTICAL) {
                    outRect.bottom = endOffset
                } else {
                    outRect.right = endOffset
                }
            }
        }
    }

    class NotSupportedLayoutManager :
        Exception("DivideItemDecoration support only LinearLayoutManager")
}
