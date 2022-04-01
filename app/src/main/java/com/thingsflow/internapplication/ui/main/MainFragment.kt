package com.thingsflow.internapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var organizationTextView: TextView
    private lateinit var repositoryTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.setOrganization("google")
        viewModel.setRepository("dagger")

        organizationTextView = binding.organizationName
        repositoryTextView = binding.repositoryName

        observe()

        //navigation test
        val navTestBtn: Button = binding.navTestBtn
        navTestBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_main_to_detail)
        }
    }

    private fun observe() = with(viewModel) {
        organization.observe(viewLifecycleOwner, Observer {
            organizationTextView.text = it
        })
        repository.observe(viewLifecycleOwner, Observer {
            repositoryTextView.text = it
        })
    }
}