package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.data.Item

object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        if ((oldItem is Item.Issue) && (newItem is Item.Issue)) return oldItem.number == newItem.number
        else if ((oldItem is Item.Image) && (newItem is Item.Image)) return oldItem.url == newItem.url
        return false
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}