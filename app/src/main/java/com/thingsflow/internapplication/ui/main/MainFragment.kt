package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
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

    private lateinit var issueRecyclerView: RecyclerView

    private lateinit var issueListAdapter: IssueListAdapter

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

        issueRecyclerView = binding.issueList

        binding.title.setOnClickListener {
            showChangeTitleDialog()
        }

        issueRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        issueListAdapter = IssueListAdapter(object : IssueSelectedListener{
            override fun onIssueSelected(issuePos: Int) {
                viewModel.userClickIssue(issuePos)
            }
        })

        issueRecyclerView.adapter = issueListAdapter

        observe()
    }

    private fun observe() = with(viewModel) {
        repositoryInfo.observe(viewLifecycleOwner, Observer {
            binding.title.text = "${it.organization} / ${it.repository}"
        })
        issueList.observe(viewLifecycleOwner, Observer {
            issueListAdapter.submitList(it)
        })
        loadSuccess.observe(viewLifecycleOwner, Observer {
            if(it == false){
                showErrorDialog()
                setIssueList(
                    viewModel.repositoryInfo.value!!.organization,
                    viewModel.repositoryInfo.value!!.repository
                )
            }
        })
        eventNavigateToDetail.observe(viewLifecycleOwner, Observer {
            it.isActive()?.let { issuePos ->
                val action = MainFragmentDirections.actionMainToDetail(issuePos)
                Navigation.findNavController(requireView()).navigate(action)
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
}