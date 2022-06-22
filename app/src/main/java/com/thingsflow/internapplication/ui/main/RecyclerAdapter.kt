package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.thingsflow.internapplication.R
import kotlinx.android.synthetic.main.recycler_content.view.*
import kotlinx.android.synthetic.main.recycler_img.view.*
import java.lang.System.load

class RecyclerAdapter(private val event: HolderEvent) : ListAdapter<Issue, RecyclerView.ViewHolder>(DIFF_GITHUB_ISSUE) {

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
                holder.bind(getItem(position) as Issue.GithubIssue)
            }
            is ImageHolder -> {
                holder.bind(getItem(position) as Issue.Image)
            }
        }
    }

    // 이미지, 텍스트에 따른 각 홀더 만들어두기
    class ContentHolder(v: View, private val event: HolderEvent) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: Issue.GithubIssue) {
            view.itemContent.text = "#${item.number} : ${item.title}"
            view.setOnClickListener {
                event.onClickIssue(item)
            }
        }
    }

    class ImageHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        fun bind(item: Issue.Image) {
            Glide.with(v).load(item.imageLink).into(v.itemImg)
            v.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.body))
                v.context.startActivity(intent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Issue.GithubIssue -> IssueType.ISSUE.ordinal
            else -> IssueType.TYPE.ordinal
        }
    }

    enum class IssueType {
        ISSUE, TYPE
    }

    interface HolderEvent {
        fun onClickIssue(issue: Issue.GithubIssue)
    }

    companion object {
        val DIFF_GITHUB_ISSUE: DiffUtil.ItemCallback<Issue> =
            object : DiffUtil.ItemCallback<Issue>() {
                override fun areItemsTheSame(
                    oldItem: Issue,
                    newItem: Issue
                ): Boolean {
                    return oldItem.body == newItem.body
                }

                override fun areContentsTheSame(
                    oldItem: Issue,
                    newItem: Issue
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}