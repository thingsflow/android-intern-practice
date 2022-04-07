package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.data.Item

object IssueListCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return if(oldItem is Item.IssueData && newItem is Item.IssueData){
            oldItem.issueNum == newItem.issueNum
        } else if(oldItem is Item.Image && newItem is Item.Image){
            oldItem.url == newItem.url
        } else{
            false
        }
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}