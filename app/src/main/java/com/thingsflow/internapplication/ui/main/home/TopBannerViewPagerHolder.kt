package com.thingsflow.internapplication.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindViewHolder
import com.thingsflow.internapplication.base.ui.list.adapter.HolderEvent
import com.thingsflow.internapplication.databinding.ItemTopBannerBinding
import com.thingsflow.internapplication.model.OnStageStory

class TopBannerViewPagerHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<OnStageStory, TopBannerViewPagerHolder.Event>(containerView, holderEvent) {
    private val binding = ItemTopBannerBinding.bind(containerView)

    override fun bind(item: OnStageStory) {
        renderUi(item)
    }

    private fun renderUi(item: OnStageStory) = with(binding) {
        title.text = item.title
        shortDesc.text = item.shortDesc
        Glide.with(root)
            .load(item.introImageUrl)
            .into(topBannerImage)
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> TopBannerViewPagerHolder = { parent, holderEvent ->
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_banner, parent, false)

            TopBannerViewPagerHolder(view, holderEvent)
        }

        val DIFF = object : DiffUtil.ItemCallback<OnStageStory>() {
            override fun areItemsTheSame(
                oldItem: OnStageStory,
                newItem: OnStageStory
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OnStageStory,
                newItem: OnStageStory
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface Event : HolderEvent {

    }
}