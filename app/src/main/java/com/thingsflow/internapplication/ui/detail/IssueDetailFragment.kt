package com.thingsflow.internapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

        viewModel.setIssueDetail(issuePos)

        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() = with(viewModel){
        issueDetail.observe(viewLifecycleOwner, Observer {
            Glide.with(binding.root)
                .load(it.userInfo.userProfile)
                .circleCrop()
                .into(binding.userProfile)

            binding.userId.text = it.userInfo.userId
            binding.body.text = it.issueBody

            (activity as AppCompatActivity).supportActionBar?.setTitle(
                "#${it.issueNum}"
            )

        })
    }
}

