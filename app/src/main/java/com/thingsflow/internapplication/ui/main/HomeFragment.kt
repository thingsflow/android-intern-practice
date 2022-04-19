package com.thingsflow.internapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.thingsflow.internapplication.base.architecture.aac.observe
import com.thingsflow.internapplication.base.architecture.base.viewbinding.BaseFragment
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindHolderFactory
import com.thingsflow.internapplication.base.ui.list.adapter.buildAdapter
import com.thingsflow.internapplication.data.model.HomeSection
import com.thingsflow.internapplication.databinding.HomeFragmentBinding
import com.thingsflow.internapplication.holder.SectionHolder
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

    private val event by lazy {
        HomeEvent(viewModel)
    }

    private val sectionAdapter by lazy {
        AutoBindHolderFactory<HomeSection>()
            .add(
                HomeSection.BannerNovel::class,
                SectionHolder.DIFF,
                event,
                SectionHolder.CREATOR
            )
            .add(
                HomeSection.GenreNovel::class,
                SectionHolder.DIFF,
                event,
                SectionHolder.CREATOR
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
            observe(sectionList) { item ->
                renderSection(item)
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }

    private fun renderSection(list: List<HomeSection>){
        sectionAdapter.submitList(list)

        with(binding) {
            homeRecyclerView.apply {
                adapter = sectionAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}