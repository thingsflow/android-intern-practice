package com.thingsflow.internapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.data.IssueData
import com.thingsflow.internapplication.databinding.IssueItemBinding
import javax.inject.Inject

class IssueListAdapter @Inject constructor() :
    ListAdapter<IssueData, IssueListAdapter.IssueListViewHolder>(IssueListCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListViewHolder {
        return IssueListViewHolder(IssueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IssueListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class IssueListViewHolder(private val binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IssueData) {
            binding.issueNum.text = item.issueNum.toString()
            binding.issueTitle.text = item.issueTitle

            binding.root.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_main_to_detail)
            }
        }
    }
}