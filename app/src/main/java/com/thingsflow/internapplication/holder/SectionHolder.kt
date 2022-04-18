package com.thingsflow.internapplication.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.*
import com.thingsflow.internapplication.data.model.HomeSection
import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.databinding.SectionItemBinding
import kotlinx.android.synthetic.main.layout_viewpager.view.*

class SectionHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<HomeSection, SectionHolder.Event>(containerView, holderEvent) {

    private var binding = SectionItemBinding.bind(containerView)

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

    interface Event : HolderEvent

    override fun bind(item: HomeSection) {
        renderUi(item)
    }

    private fun renderUi(item: HomeSection) {
        renderBannerNovelList(item.bannerNovel)
        renderGenreNovelList(item.genreNovel)
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
                if (position == 0 || position == bannerAdapter.itemCount - 1) {
                    tab.view.visibility = View.GONE
                }
            }.attach()
        }
    }

    private fun renderGenreNovelList(list: List<NovelCover>) {
        genreAdapter.submitList(list)

        with(binding) {
            genreRecyclerView.apply {
                adapter = genreAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> SectionHolder = { parent, holderEvent ->
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.section_item, parent, false)

            SectionHolder(view, holderEvent)
        }

        val DIFF = object : DiffUtil.ItemCallback<HomeSection>() {
            override fun areItemsTheSame(oldItem: HomeSection, newItem: HomeSection): Boolean {
                return oldItem.bannerNovel == newItem.bannerNovel && oldItem.genreNovel == newItem.genreNovel
            }

            override fun areContentsTheSame(oldItem: HomeSection, newItem: HomeSection): Boolean {
                return oldItem == newItem
            }
        }
    }
}