package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thingsflow.internapplication.R
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

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

        organizationTextView = binding.organizationName
        repositoryTextView = binding.repositoryName
        issueRecyclerView = binding.issueList

        //viewModel.setIssueList("google", "dagger")

        binding.title.setGroupOnClickListener {
            showChangeTitleDialog()
        }

        issueRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        issueRecyclerView.adapter = issueListAdapter

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
        loadSuccess.observe(viewLifecycleOwner, Observer {
            if(it == false){
                showErrorDialog()
            }
        })
    }

    private fun showChangeTitleDialog() {
        val dialog = ChangeTitleDialog()
        dialog.show(childFragmentManager, "ChangeTitleDialog")
    }

    private fun showErrorDialog() {
        val dialog = ErrorDialog()
        dialog.show(childFragmentManager, "ErrorDialog")
    }

    private fun Group.setGroupOnClickListener(listener: View.OnClickListener){
        referencedIds.forEach {
            rootView.findViewById<View>(it).setOnClickListener(listener)
        }
    }
}