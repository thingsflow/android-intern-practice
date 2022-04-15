package com.thingsflow.internapplication.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.base.architecture.aac.event
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.buildViewPagerAdapter
import com.thingsflow.internapplication.base.ui.list.view.InfiniteViewPager2
import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.databinding.HomeFragmentBinding
import com.thingsflow.internapplication.holder.StoryCoverHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment<HomeViewModel, HomeFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override val viewModel by viewModels<HomeViewModel>()

    override val backFunc: (() -> Unit)
        get() = ::backPressed

    private val event by lazy{
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

    private fun backPressed() {
        Toast.makeText(requireContext(), "Back Pressed", Toast.LENGTH_SHORT).show()
    }

    override fun loadData() {

    }

    override fun setupUi() = with(binding) {
        bannerViewPager.setAdapter(bannerAdapter)
        bannerViewPager.startAutoSlide(3000)
        viewModel.loadTest()

    }

    override fun observeUi() {
        with(viewModel) {
            observe(testText) {
                //binding.message.text = it
            }
            observe(novelList){ item ->
                item.forEach {
                    Log.d("CHECK_LIST", "${it.title}")
                }
                bannerAdapter.submitList(item)
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }


}