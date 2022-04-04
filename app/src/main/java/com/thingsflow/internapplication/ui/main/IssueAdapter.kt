package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.data.Issue
import com.thingsflow.internapplication.databinding.ItemIssueBinding
import javax.inject.Inject

class IssueAdapter @Inject constructor() : ListAdapter<Any, IssueAdapter.ViewHolder>(IssueDiffCallback) {
    companion object {
        const val URL_WEBPAGE = "https://thingsflow.com/ko/home"
    }

    lateinit var parentView: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemIssueBinding = ItemIssueBinding.inflate(layoutInflater, parent, false)

        parentView = parent

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item is String) {
            holder.bindImage(item)
        } else if (item is Issue) {
            holder.bind(item.title, item.number, position)
        }
    }

    inner class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueTitle: String, issueNumber: Int, issueIdx: Int) {
            with(binding) {
                root.removeView(binding.bannerImg)

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