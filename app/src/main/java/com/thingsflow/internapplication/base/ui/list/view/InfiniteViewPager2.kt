package com.thingsflow.internapplication.base.ui.list.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindViewPagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class InfiniteViewPager2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes) {

    private val viewPager2: ViewPager2 by lazy {
        findViewById(R.id.view_pager_infinite)
    }

    private val internalRecyclerView by lazy {
        viewPager2.getChildAt(0) as RecyclerView
    }
    private var onPageChangeCallback: ViewPager2.OnPageChangeCallback? = null
    private var autoSlideDuration: Long = 3000
    private var coroutineJob: Job? = null
    private val timer = (0..Int.MAX_VALUE)
        .asSequence()
        .asFlow()
        .onEach { delay(autoSlideDuration) }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.layout_viewpager, this, true)
    }

    private fun getItemCount(): Int {
        return viewPager2.adapter?.itemCount ?: 0
    }

    private fun next() {
        viewPager2.setCurrentItem(viewPager2.currentItem + 1, true)
    }

    fun setAdapter(adapter: AutoBindViewPagerAdapter<*>) {
        viewPager2.adapter = adapter.apply {
            onCurrentListChangedListener = { previousList, currentList ->
                internalRecyclerView.apply {
                    if (previousList.size == 0 && currentList.size > 0) {
                        viewPager2.setCurrentItem(1, false)
                    }
                }
            }
        }

        internalRecyclerView.apply {
            addOnScrollListener(
                InfiniteScrollBehaviour(
                    ::getItemCount,
                    layoutManager as LinearLayoutManager
                )
            )
        }

        if (adapter.currentList.size > 0) {
            viewPager2.setCurrentItem(1, false)
        }
    }

    fun startAutoSlide(durationMsec: Int) {
        autoSlideDuration = durationMsec.toLong()
        coroutineJob = CoroutineScope(Dispatchers.Main).launch {
            timer.collect {
                next()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        coroutineJob?.cancel()
        onPageChangeCallback?.let {
            viewPager2.unregisterOnPageChangeCallback(it)
        }
        onPageChangeCallback = null
    }

    fun registerOnPageChangeCallback(@NonNull callback: ViewPager2.OnPageChangeCallback) {
        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                super.onPageSelected(position)
                callback.onPageSelected(
                    if (position - 1 < 0) {
                        (viewPager2.adapter?.itemCount ?: 2) - 2
                    } else {
                        position - 1
                    }
                )
            }
        }
        onPageChangeCallback?.let {
            viewPager2.registerOnPageChangeCallback(it)
        }
    }

    inner class InfiniteScrollBehaviour(
        private val itemCountFunc: () -> Int,
        private val layoutManager: LinearLayoutManager
    ) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstItemVisible = layoutManager.findFirstVisibleItemPosition()
            val lastItemVisible = layoutManager.findLastVisibleItemPosition()
            val itemCount = itemCountFunc.invoke()

            if (firstItemVisible == (itemCount - 1) && dx > 0) {
                recyclerView.scrollToPosition(1)
            } else if (lastItemVisible == 0 && dx < 0) {
                recyclerView.scrollToPosition(itemCount - 2)
            }
        }
    }
}
