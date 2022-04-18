package com.thingsflow.internapplication.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.architecture.base.dpToPx
import com.thingsflow.internapplication.base.ui.list.adapter.*
import com.thingsflow.internapplication.databinding.ItemWholeSectionBinding
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.model.WholeSectionItem
import kotlinx.android.synthetic.main.layout_viewpager.view.*

class WholeSectionHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<WholeSectionItem, WholeSectionHolder.Event>(containerView, holderEvent) {
    private val binding = ItemWholeSectionBinding.bind(containerView)

    private val storyAdapter by lazy {
        AutoBindHolderFactory<OnStageStory>()
            .add(
                OnStageStory::class,
                StoriesHolder.DIFF,
                event,
                StoriesHolder.CREATOR
            )
            .buildAdapter()
    }

    private val topBannerAdapter by lazy {
        AutoBindHolderFactory<OnStageStory>()
            .add(
                OnStageStory::class,
                TopBannerViewPagerHolder.DIFF,
                event,
                TopBannerViewPagerHolder.CREATOR
            )
            .buildViewPagerAdapter()
    }

    override fun bind(item: WholeSectionItem) {
        renderUi(item)
    }

    private fun renderUi(item: WholeSectionItem) {
        val event = HomeEvent()

        setupUi()

        storyAdapter.submitList(item.onStageStoriesByGenre)
        topBannerAdapter.submitList(item.topBannerStories)
    }

    private fun setupUi(): Unit = with(binding) {
        recyclerByGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = storyAdapter
            val padding = 15f.dpToPx(context)
            addItemDecoration(
                IntervalItemDecoration(
                    startOffset = padding,
                    endOffset = padding,
                    offset = padding
                )
            )
        }

        topBannerViewPager.apply {
            setAdapter(topBannerAdapter)
            startAutoSlide(HomeFragment.TOP_BANNER_SCROLL_DURATION)
            TabLayoutMediator(tabLayout, view_pager_infinite) { tab, position ->
                if (position == 0 || position == topBannerAdapter.itemCount - 1) {
                    tab.view.visibility = View.GONE
                }
            }.attach()
        }
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> WholeSectionHolder = { parent, holderEvent ->
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_whole_section, parent, false)

            WholeSectionHolder(view, holderEvent)
        }

        val DIFF = object : DiffUtil.ItemCallback<WholeSectionItem>() {
            override fun areItemsTheSame(
                oldItem: WholeSectionItem,
                newItem: WholeSectionItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WholeSectionItem,
                newItem: WholeSectionItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface Event : HolderEvent {

    }
}