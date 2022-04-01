package com.thingsflow.internapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.databinding.ItemIssueBinding
import javax.inject.Inject

class IssueAdapter @Inject constructor() : ListAdapter<Issue, IssueAdapter.ViewHolder>(IssueDiffCallback) {
    companion object {
        const val POS_BANNER = 4
        const val URL_BANNER = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
        const val URL_WEBPAGE = "https://thingsflow.com/ko/home"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemIssueBinding = ItemIssueBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == POS_BANNER) {
            holder.bindImage(URL_BANNER)
            return
        }

        var idx = position
        if (position > POS_BANNER) {
            idx = position - 1
        }

        val issue = getItem(idx)
        holder.bind("#${issue.number}: ${issue.title}")
    }

    inner class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueText: String) {
            with(binding) {
                root.removeView(binding.bannerImg)
                this.issueText.setText(issueText)

                this.issueText.setOnClickListener(View.OnClickListener {
                    // TODO: 이슈 상세 화면으로 이동
                })
            }
        }

        fun bindImage(url: String) {
            with(binding) {
                root.removeView(binding.issueText)
                Glide.with(root.context)
                    .load(url)
                    .into(bannerImg)

                bannerImg.setOnClickListener(View.OnClickListener {
                    // TODO: 웹페이지 링크 열기
                })
            }
        }
    }
}