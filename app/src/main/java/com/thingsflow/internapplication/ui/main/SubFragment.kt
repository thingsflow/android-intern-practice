package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thingsflow.internapplication.MainActivity
import com.thingsflow.internapplication.databinding.SubFragmentBinding


class SubFragment: Fragment() {

    companion object {
        fun newInstance() = SubFragment()
    }

    private var _binding: SubFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var number: String
    private lateinit var content: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("arg", arguments.toString())
        number = arguments?.get("number").toString()
        content = arguments?.get("content").toString()
        _binding = SubFragmentBinding.inflate(inflater, container, false)
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
        onResume(number)
    }

    fun onResume(s: String) {
        //super.onResume()
        val activity = activity
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle("#${s}")
        }
    }
}