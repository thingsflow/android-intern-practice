package com.thingsflow.internapplication.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.thingsflow.internapplication.MainActivity
import com.thingsflow.internapplication.base.architecture.aac.event
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.dpToPx
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.IntervalItemDecoration
import com.thingsflow.internapplication.base.ui.list.adapter.buildAdapter
import com.thingsflow.internapplication.base.ui.list.adapter.buildViewPagerAdapter
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import com.thingsflow.internapplication.model.OnStageStory
import com.thingsflow.internapplication.utils.hideLoading
import com.thingsflow.internapplication.utils.showLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_viewpager.view.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
        const val TOP_BANNER_SCROLL_DURATION = 3000
    }

    override val viewModel by viewModels<HomeViewModel>()

    private val event by lazy {
        HomeEvent(viewModel)
    }

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

    override fun loadData() {
        viewModel.load()
    }

    override fun setupUi(): Unit = with(binding) {
        recyclerByGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = storyAdapter
            val padding = 15f.dpToPx(requireContext())
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
            startAutoSlide(TOP_BANNER_SCROLL_DURATION)
            TabLayoutMediator(tabLayout, view_pager_infinite) { tab, position ->
                if (position == 0 || position == topBannerAdapter.itemCount - 1) {
                    tab.view.visibility = View.GONE
                }
            }.attach()
        }
    }

    private fun renderStoryByGenreList(list: List<OnStageStory>) {
        storyAdapter.submitList(list)
    }

    private fun renderTopBannerList(list: List<OnStageStory>) {
        topBannerAdapter.submitList(list)
    }

    override fun observeUi() {
        with(viewModel) {
            observe(onStageStoriesByGenre) {
                renderStoryByGenreList(it)
            }
            observe(topBannerStories) {
                renderTopBannerList(it)
            }

            event(loadingEvent) {
                if (it) showLoading()
                else hideLoading()
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding {
        return MainFragmentBinding.inflate(inflater, container, false)
    }

    override val backFunc: (() -> Unit)?
        get() = null
}