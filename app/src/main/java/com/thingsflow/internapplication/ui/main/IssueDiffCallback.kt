package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.data.Issue

object IssueDiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is Issue && newItem is Issue) return oldItem.number == newItem.number
        else if (oldItem is String && newItem is String) return oldItem == newItem
        return false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is Issue && newItem is Issue) return oldItem == newItem
        else if (oldItem is String && newItem is String) return oldItem == newItem
        return false
    }
}