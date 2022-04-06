package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.databinding.IssueItemBinding
import javax.inject.Inject

class IssueListAdapter @Inject constructor(private val listener: IssueSelectedListener) :
    ListAdapter<Item, IssueListAdapter.IssueListViewHolder>(IssueListCallBack) {

    private val HOME_PAGE_URL = "https://thingsflow.com/ko/home"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListViewHolder {
        return IssueListViewHolder(IssueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IssueListViewHolder, position: Int) {
        if(currentList[position] is Item.IssueData){
            holder.bind(currentList[position] as Item.IssueData, position)
        }
        else if(currentList[position] is Item.Image){
            holder.bindBanner(currentList[position] as Item.Image)
        }
    }

    inner class IssueListViewHolder(private val binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item.IssueData, position: Int) {
            val itemText = "#${item.issueNum}: ${item.issueTitle}"
            binding.issueItem.text = itemText

            binding.root.setOnClickListener{
                listener.onIssueSelected(position)
            }
        }

        fun bindBanner(item: Item.Image){
            Glide.with(binding.root)
                .load(item.url)
                .into(binding.bannerImg)

            binding.issueItem.visibility = View.INVISIBLE

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(HOME_PAGE_URL))
                it.context.startActivity(intent)
            }
        }
    }
}