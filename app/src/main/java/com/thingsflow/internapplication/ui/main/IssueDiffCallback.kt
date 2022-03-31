package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil

object IssueDiffCallback : DiffUtil.ItemCallback<Issue>() {
    override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem == newItem
    }
}