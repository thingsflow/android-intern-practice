package com.thingsflow.internapplication.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.dpToPx
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.IntervalItemDecoration
import com.thingsflow.internapplication.base.ui.list.adapter.buildAdapter
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import com.thingsflow.internapplication.model.OnStageStory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
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

    override fun loadData() {
        viewModel.load()
    }

    override fun setupUi() = with(binding) {
        recyclerByGenre.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerByGenre.adapter = storyAdapter
        val padding = 15f.dpToPx(requireContext())
        recyclerByGenre.addItemDecoration(
            IntervalItemDecoration(
                startOffset = padding,
                endOffset = padding,
                offset = padding
            )
        )
    }

    private fun renderStoryByGenreList(list: List<OnStageStory>) {
        storyAdapter.submitList(list)
    }

    override fun observeUi() {
        with(viewModel) {
            observe(onStageStoriesByGenre) {
                renderStoryByGenreList(it)
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