package com.thingsflow.internapplication.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.ui.list.adapter.AutoBindViewHolder
import com.thingsflow.internapplication.base.ui.list.adapter.HolderEvent
import com.thingsflow.internapplication.data.model.NovelCover
import com.thingsflow.internapplication.databinding.StoryCoverItemBinding

class StoryCoverHolder(
    containerView: View,
    holderEvent: HolderEvent
) : AutoBindViewHolder<NovelCover, StoryCoverHolder.Event>(containerView, holderEvent) {

    private var binding = StoryCoverItemBinding.bind(containerView)

    interface Event : HolderEvent {

    }

    override fun bind(item: NovelCover) {
        renderUi(item)
    }

    private fun renderUi(item: NovelCover) = with(binding){
        novelTitle.text = item.title
        novelDesc.text = item.oneLineDesc

        Glide.with(this.root)
            .load(item.mainImgUrl)
            .centerCrop()
            .into(this.bannerImg)

        Glide.with(this.root)
            .load(R.drawable.ic_logo)
            .into(this.novelLogo)
    }

    companion object {
        val CREATOR: (ViewGroup, HolderEvent) -> StoryCoverHolder = { parent, holderEvent ->
            val view = LayoutInflater.from(parent.context).inflate(R.layout.story_cover_item, parent, false)

            StoryCoverHolder(view, holderEvent)
        }

        val DIFF = object : DiffUtil.ItemCallback<NovelCover>() {
            override fun areItemsTheSame(oldItem: NovelCover, newItem: NovelCover): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NovelCover, newItem: NovelCover): Boolean {
                return oldItem == newItem
            }
        }
    }
}