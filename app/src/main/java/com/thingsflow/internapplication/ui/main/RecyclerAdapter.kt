package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import kotlinx.android.synthetic.main.recycler_content.view.*
import kotlinx.android.synthetic.main.recycler_img.view.*

class RecyclerAdapter(private val event: HolderEvent) : ListAdapter<IssueData, RecyclerView.ViewHolder>(DIFF_GITHUB_ISSUE) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            IssueType.ISSUE.ordinal -> {//content
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_content, parent, false)
                ContentHolder(view, event)
            }
            else -> {//image
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_img, parent, false)
                ImageHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentHolder -> {
                holder.bind(getItem(position))
            }
            is ImageHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    // 이미지, 텍스트에 따른 각 홀더 만들어두기
    class ContentHolder(v: View, private val event: HolderEvent) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: IssueData) {
            var issueContent = Issue.GithubIssue(
                url = item.url,
                number = item.number,
                title = item.title,
                body = item.body
            )
            view.itemContent.text = "#${item.number} : ${item.title}"
            view.setOnClickListener {
                event.onClickIssue(issueContent)
            }
        }
    }

    class ImageHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: IssueData) {
            var issueImage = Issue.Image(
                imageLink = item.url,
                body = item.body
            )
            Glide.with(v).load(issueImage.imageLink).into(v.itemImg)
            v.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(issueImage.body))
                v.context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            4 -> IssueType.TYPE.ordinal
            else -> IssueType.ISSUE.ordinal
        }
    }

    enum class IssueType {
        ISSUE, TYPE
    }

    interface HolderEvent {
        fun onClickIssue(issue: Issue.GithubIssue)
    }

    companion object {
        val DIFF_GITHUB_ISSUE: DiffUtil.ItemCallback<IssueData> =
            object : DiffUtil.ItemCallback<IssueData>() {
                override fun areItemsTheSame(
                    oldItem: IssueData,
                    newItem: IssueData
                ): Boolean {
                    return oldItem.url == newItem.url
                }

                override fun areContentsTheSame(
                    oldItem: IssueData,
                    newItem: IssueData
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}