package com.thingsflow.internapplication.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.*
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.databinding.ItemIssueBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class IssueAdapter @Inject constructor(@ActivityContext private val context: Context) : ListAdapter<Issue, IssueAdapter.ViewHolder>(IssueDiffCallback) {
    companion object {
        const val POS_BANNER = 4
        const val URL_BANNER = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
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
        if (position == POS_BANNER) {
            holder.bindImage(URL_BANNER)
            return
        }

        var idx = position
        if (position > POS_BANNER) {
            idx = position - 1
        }

        val issue = getItem(idx)
        holder.bind(issue.title, issue.number, idx)
    }

    inner class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueTitle: String, issueNumber: Int, issueIdx: Int) {
            with(binding) {
                root.removeView(binding.bannerImg)

                with(binding) {
                    issueText.setText("#${issueNumber}: ${issueTitle}")
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
                    context.startActivity(intent)
                })
            }
        }
    }
}