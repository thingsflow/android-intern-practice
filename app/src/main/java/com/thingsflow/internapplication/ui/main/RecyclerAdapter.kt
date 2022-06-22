package com.thingsflow.internapplication.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.thingsflow.internapplication.R
import kotlinx.android.synthetic.main.recycler_content.view.*
import kotlinx.android.synthetic.main.recycler_img.view.*

class RecyclerAdapter(private val frag: Fragment, private val img_url: String, private val glide: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val issueList = mutableListOf<Issues>()
    override fun getItemCount(): Int = issueList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType){
            1 -> {//content
                val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_content, parent, false)
                return RecyclerAdapter.ContentHolder(view)
            }
            else -> {//image
                val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_img, parent, false)
                return RecyclerAdapter.ImageHolder(view)
            }

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContentHolder -> {
                holder.bind(issueList[position], frag)
            }
            is ImageHolder -> {
                holder.bind(issueList[position], glide, img_url)
            }
        }
    }

    // 이미지, 텍스트에 따른 각 홀더 만들어두기
    class ContentHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: Issues, frag: Fragment) {
            view.itemContent.text = "#${item.number} : ${item.title}"
            view.setOnClickListener(View.OnClickListener { it ->
                val bundle = Bundle()
                bundle.putString("number", item.number)
                bundle.putString("content", item.body)
                val subFrag = DetailIssueFragment.newInstance()
                subFrag.arguments = bundle
                frag.parentFragmentManager.beginTransaction()
                    .replace(R.id.container, subFrag)
                    .addToBackStack(null).commit()
            })
        }
    }
    class ImageHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item: Issues, glide: RequestManager, img_url: String) {
            glide.load(img_url).into(view.itemImg)
            view.setOnClickListener(View.OnClickListener { it ->
                var intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.body))
                view.context.startActivity(intent)
            })
        }
    }

    fun setNewItems(newItems: MutableList<Issues>) {
        issueList.clear()
        issueList.addAll(newItems)
        Log.d("LOG", issueList.toString())
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        when(position){
            4 -> return 2
            else -> return 1
        }
    }

}