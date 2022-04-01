package com.thingsflow.internapplication.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.databinding.DetailFragmentBinding
import com.thingsflow.internapplication.ui.main.Issue
import com.thingsflow.internapplication.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Use the ViewModel
        val args: DetailFragmentArgs by navArgs()
        val issueIdx = args.issueIdx

        val issue: Issue? = viewModel.issues.value?.get(issueIdx)
        if (issue == null) {
            Log.e("Null Error", "Detail argument issue is null")
            return
        }

        with(binding) {
            Glide.with(root.context)
                .load(issue.user.profileUrl)
                .into(profileIv)
            profileNameTv.setText(issue.user.id)
            issueBodyTv.setText(issue.body)

        }
    }
}