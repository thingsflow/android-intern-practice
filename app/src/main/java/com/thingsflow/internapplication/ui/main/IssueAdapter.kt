package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.databinding.ItemIssueBinding

class IssueAdapter constructor(private val onClickIssueListenerImpl: OnClickIssueListener) : ListAdapter<Item, IssueAdapter.ViewHolder>(ItemDiffCallback) {
    companion object {
        const val URL_WEBPAGE = "https://thingsflow.com/ko/home"
    }

    interface OnClickIssueListener {
        fun onClick(issueIdx: Int)
    }

    private lateinit var parentView: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemIssueBinding = ItemIssueBinding.inflate(layoutInflater, parent, false)

        parentView = parent

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(val item = getItem(position)) {
            is Item.Issue -> holder.bind(item.title, item.number, position)
            is Item.Image -> holder.bindImage(item.url)
        }
    }

    inner class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueTitle: String, issueNumber: Int, issueIdx: Int) {
            with(binding) {
                bannerImg.visibility = View.GONE
                issueText.visibility = View.VISIBLE

                issueText.text = "#${issueNumber}: $issueTitle"
                issueText.setOnClickListener(View.OnClickListener {
                    onClickIssueListenerImpl.onClick(issueIdx)
                })
            }
        }

        fun bindImage(url: String) {
            with(binding) {
                bannerImg.visibility = View.VISIBLE
                issueText.visibility = View.GONE

                Glide.with(root.context)
                    .load(url)
                    .into(bannerImg)

                bannerImg.setOnClickListener(View.OnClickListener {
                    // 웹페이지 링크 열기
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(URL_WEBPAGE))
                    binding.root.context.startActivity(intent)
                })
            }
        }
    }
}