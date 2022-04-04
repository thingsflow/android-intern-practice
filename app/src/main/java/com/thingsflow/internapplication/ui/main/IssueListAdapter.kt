package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.data.IssueData
import com.thingsflow.internapplication.databinding.IssueItemBinding
import javax.inject.Inject

class IssueListAdapter @Inject constructor() :
    ListAdapter<IssueData, IssueListAdapter.IssueListViewHolder>(IssueListCallBack) {

    private val BANNER_IMG_URL = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    private val POS = 4
    private val HOME_PAGE_URL = "https://thingsflow.com/ko/home"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueListViewHolder {
        return IssueListViewHolder(IssueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IssueListViewHolder, position: Int) {
        if(position == POS){
            holder.bindBanner()
        }
        else{
            holder.bind(currentList[position])
        }
    }

    inner class IssueListViewHolder(private val binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IssueData) {
            binding.issueNum.text = "#" + item.issueNum.toString() + ": "
            binding.issueTitle.text = item.issueTitle

            binding.root.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_main_to_detail)
            }
        }

        fun bindBanner(){
            Glide.with(binding.root)
                .load(BANNER_IMG_URL)
                .into(binding.bannerImg)

            binding.issueNum.visibility = View.INVISIBLE
            binding.issueTitle.visibility = View.INVISIBLE

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(HOME_PAGE_URL))
                it.context.startActivity(intent)
            }
        }
    }
}