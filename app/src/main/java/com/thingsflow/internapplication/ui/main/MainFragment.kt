package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var organizationTextView: TextView
    private lateinit var repositoryTextView: TextView
    private lateinit var issueRecyclerView: RecyclerView

    private val issueListAdapter = IssueListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        organizationTextView = binding.organizationName
        repositoryTextView = binding.repositoryName
        issueRecyclerView = binding.issueList

        val title = binding.title
        title.setOnClickListener {
            showDialog()
        }

        issueRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        issueRecyclerView.adapter = issueListAdapter

        viewModel.setOrganization("google")
        viewModel.setRepository("dagger")
        viewModel.setIssueList("google", "dagger")

        observe()
    }

    private fun observe() = with(viewModel) {
        organization.observe(viewLifecycleOwner, Observer {
            organizationTextView.text = it
        })
        repository.observe(viewLifecycleOwner, Observer {
            repositoryTextView.text = it
        })
        issueList.observe(viewLifecycleOwner, Observer {
            issueListAdapter.submitList(it)
        })
    }

    private fun showDialog() {
        val dialog = ChangeTitleDialog()
        dialog.show(childFragmentManager, "ChangeTitleDialog")
    }
}