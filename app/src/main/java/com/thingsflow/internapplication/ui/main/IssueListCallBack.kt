package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.data.Item

object IssueListCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}