package com.thingsflow.internapplication.ui.main.home

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindViewHolder
import com.thingsflow.internapplication.base.ui.list.adapter.HolderEvent
import com.thingsflow.internapplication.databinding.ItemStoryBinding
import com.thingsflow.internapplication.model.OnStageStory

class StoriesHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<OnStageStory, StoriesHolder.Event>(containerView, holderEvent) {
    private val binding = ItemStoryBinding.bind(containerView)

    override fun bind(item: OnStageStory) {
        renderUi(item)
    }

    private fun renderUi(item: OnStageStory) = with(binding) {
        storyTitle.text = item.title
        storyDesc.text = item.shortDesc
        Glide.with(root)
            .load(item.wideImageUrl)
            .error(R.drawable.wide_image_sample)
            .into(storyWideImage)
        if (item.isFinished) finishIcon.visibility = View.VISIBLE
        else finishIcon.visibility = View.GONE
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> StoriesHolder = { parent, holderEvent ->
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_story, parent, false)

            StoriesHolder(view, holderEvent)
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