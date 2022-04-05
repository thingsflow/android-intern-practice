package com.thingsflow.internapplication.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.thingsflow.internapplication.data.Item
import com.thingsflow.internapplication.databinding.DetailFragmentBinding
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

        val args: DetailFragmentArgs by navArgs()
        val issueIdx = args.issueIdx

        val arg: Any? = viewModel.issues.value?.get(issueIdx)
        if (arg !is Item.Issue) {
            Log.e("Type Error", "Detail argument issue is not Issue type")
        }
        if (arg == null) {
            Log.e("Null Error", "Detail argument issue is null")
            return
        }

        val issue = arg as Item.Issue

        (activity as AppCompatActivity).supportActionBar?.title = "#${issue.number}"

        with(binding) {
            Glide.with(root.context)
                .load(issue.user.profileUrl)
                .into(profileIv)
            profileNameTv.text = issue.user.id
            issueBodyTv.text = issue.body

        }
    }
}