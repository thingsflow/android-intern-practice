package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.navigation.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.databinding.ItemIssueBinding
import javax.inject.Inject

class IssueAdapter @Inject constructor() : ListAdapter<Item, IssueAdapter.ViewHolder>(ItemDiffCallback) {
    companion object {
        const val URL_WEBPAGE = "https://thingsflow.com/ko/home"
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
                root.removeView(binding.bannerImg)
                if (!root.contains(binding.issueText)) root.addView(binding.issueText)

                with(binding) {
                    issueText.text = "#${issueNumber}: $issueTitle"
                    issueText.setOnClickListener(View.OnClickListener {
                        val navDirection: NavDirections = MainFragmentDirections.actionMainFragmentToDetailFragment(issueIdx)
                        val navController = root.findNavController()

                        navController.navigate(navDirection)
                    })
                }
            }
        }

        fun bindImage(url: String) {
            with(binding) {
                root.removeView(binding.issueText)
                if (!root.contains(binding.bannerImg)) root.addView(binding.bannerImg)

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