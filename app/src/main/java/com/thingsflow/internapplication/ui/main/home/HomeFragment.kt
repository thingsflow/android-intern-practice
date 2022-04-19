package com.thingsflow.internapplication.ui.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.thingsflow.internapplication.base.architecture.aac.event
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.dpToPx
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.IntervalItemDecoration
import com.thingsflow.internapplication.base.ui.list.adapter.buildAdapter
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import com.thingsflow.internapplication.model.WholeSectionItem
import com.thingsflow.internapplication.utils.hideLoading
import com.thingsflow.internapplication.utils.showLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
        const val TOP_BANNER_SCROLL_DURATION = 3000
    }

    override val viewModel by viewModels<HomeViewModel>()

    private val event by lazy {
        HomeEvent()
    }

    private val wholeSectionAdapter by lazy {
        AutoBindHolderFactory<WholeSectionItem>()
            .add(
                WholeSectionItem::class,
                WholeSectionHolder.DIFF,
                event,
                WholeSectionHolder.CREATOR
            )
            .buildAdapter()
    }

    override fun loadData() {
        viewModel.load()
    }

    override fun setupUi(): Unit = with(binding) {
        wholeSectionRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = wholeSectionAdapter
            val padding = 15f.dpToPx(context)
            addItemDecoration(
                IntervalItemDecoration(
                    offset = padding
                )
            )
        }
    }

    private fun renderWholeSectionItems(item: List<WholeSectionItem>) {
        wholeSectionAdapter.submitList(item)
    }

    override fun observeUi() {
        with(viewModel) {
            observe(wholeSectionItems) {
                renderWholeSectionItems(it)
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