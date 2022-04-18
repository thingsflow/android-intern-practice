package com.thingsflow.internapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.buildAdapter
import com.thingsflow.internapplication.base.ui.list.adapter.buildViewPagerAdapter
import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.databinding.HomeFragmentBinding
import com.thingsflow.internapplication.holder.GenreCoverHolder
import com.thingsflow.internapplication.holder.StoryCoverHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_viewpager.view.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment<HomeViewModel, HomeFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel by viewModels<HomeViewModel>()

    override val backFunc: (() -> Unit)
        get() = ::backPressed

    private val event by lazy {
        HomeEvent(viewModel)
    }


    private val bannerAdapter by lazy {
        AutoBindHolderFactory<NovelCover>()
            .add(
                NovelCover::class,
                StoryCoverHolder.DIFF,
                event,
                StoryCoverHolder.CREATOR
            )
            .buildViewPagerAdapter()
    }

    private val genreAdapter by lazy {
        AutoBindHolderFactory<NovelCover>()
            .add(
                NovelCover::class,
                GenreCoverHolder.DIFF,
                event,
                GenreCoverHolder.CREATOR
            )
            .buildAdapter()
    }

    private fun backPressed() {
        Toast.makeText(requireContext(), "Back Pressed", Toast.LENGTH_SHORT).show()
    }

    override fun loadData() {
        viewModel.loadNovelList()
    }

    override fun setupUi() {

    }

    override fun observeUi() {
        with(viewModel) {
            observe(novelList) { item ->
                renderBannerNovelList(item)
                renderGenreNovelList(item)
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }

    private fun renderBannerNovelList(list: List<NovelCover>) {
        val BANNER_AUTO_SLIDE_DURATION = 3000
        val BANNER_ITEM_NUM = 3
        val bannerNovelList: MutableList<NovelCover> = mutableListOf()

        for (i in 0 until BANNER_ITEM_NUM) {
            bannerNovelList.add(i, list[i])
        }

        bannerAdapter.submitList(bannerNovelList)

        with(binding) {
            bannerViewPager.apply {
                setAdapter(bannerAdapter)
                startAutoSlide(BANNER_AUTO_SLIDE_DURATION)
            }

            TabLayoutMediator(
                viewPagerIndicator,
                bannerViewPager.view_pager_infinite
            ) { tab, position ->
                if(position == 0 || position == bannerAdapter.itemCount - 1){
                    tab.view.visibility = View.GONE
                }
            }.attach()
        }
    }

    private fun renderGenreNovelList(list: List<NovelCover>){

        genreAdapter.submitList(list)

        with(binding){
            genreRecyclerView.apply {
                adapter = genreAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}