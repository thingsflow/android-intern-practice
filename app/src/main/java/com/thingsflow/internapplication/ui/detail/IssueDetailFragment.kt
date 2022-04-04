package com.thingsflow.internapplication.ui.detail

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.databinding.IssueDetailFragmentBinding
import com.thingsflow.internapplication.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssueDetailFragment : Fragment() {

    companion object {
        fun newInstance() = IssueDetailFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: IssueDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IssueDetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: IssueDetailFragmentArgs by navArgs()
        val issuePos = args.issuePosition

        val issueNumber = viewModel.issueList.value?.get(issuePos)?.issueNum
        val userID = viewModel.issueList.value?.get(issuePos)?.userInfo?.userId
        val userProfileURL = viewModel.issueList.value?.get(issuePos)?.userInfo?.userProfile
        val issueBody = viewModel.issueList.value?.get(issuePos)?.issueBody

        (activity as AppCompatActivity).supportActionBar?.setTitle(
            "#${issueNumber.toString()}"
        )

        Glide.with(binding.root)
            .load(userProfileURL)
            .into(binding.userProfile)

        binding.userId.text = userID
        binding.body.text = issueBody

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

