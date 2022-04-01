package com.thingsflow.internapplication.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.thingsflow.internapplication.data.IssueData

object IssueListCallBack : DiffUtil.ItemCallback<IssueData>() {
    override fun areItemsTheSame(oldItem: IssueData, newItem: IssueData): Boolean {
        return oldItem.issueNum == newItem.issueNum
    }

    override fun areContentsTheSame(oldItem: IssueData, newItem: IssueData): Boolean {
        return oldItem == newItem
    }
}