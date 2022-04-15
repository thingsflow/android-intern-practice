package com.thingsflow.internapplication.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindViewHolder
import com.thingsflow.internapplication.base.ui.list.adapter.HolderEvent
import com.thingsflow.internapplication.databinding.ItemNovelCoverBinding
import com.thingsflow.internapplication.model.OnStageNovelCover

class NovelCoverHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<OnStageNovelCover, NovelCoverHolder.Event>(containerView, holderEvent) {
    private val binding = ItemNovelCoverBinding.bind(containerView)

    override fun bind(item: OnStageNovelCover) {
        renderUi(item)
    }

    private fun renderUi(item: OnStageNovelCover) = with(binding) {
        // TODO: implement
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> NovelCoverHolder = { parent, holderEvent ->
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_novel_cover, parent, false)

            NovelCoverHolder(view, holderEvent)
        }

        val DIFF = object : DiffUtil.ItemCallback<OnStageNovelCover>() {
            override fun areItemsTheSame(
                oldItem: OnStageNovelCover,
                newItem: OnStageNovelCover
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OnStageNovelCover,
                newItem: OnStageNovelCover
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface Event : HolderEvent {

    }
}