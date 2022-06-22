package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thingsflow.internapplication.MainActivity
import com.thingsflow.internapplication.databinding.DetailIssueFragmentBinding


class DetailIssueFragment: Fragment() {

    companion object {
        fun newInstance() = DetailIssueFragment()
    }

    private var _binding: DetailIssueFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var number: String
    private lateinit var content: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        number = arguments?.get("number").toString()
        content = arguments?.get("content").toString()
        _binding = DetailIssueFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        binding.detailIssue.setMovementMethod(ScrollingMovementMethod());
        binding.detailIssue.text = content
    }

    override fun onResume() {
        super.onResume()
        val activity = activity
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle("#${number}")
        }
    }
}