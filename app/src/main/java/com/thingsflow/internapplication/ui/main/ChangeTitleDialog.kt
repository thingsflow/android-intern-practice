package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.thingsflow.internapplication.databinding.ChangeTitleDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeTitleDialog: DialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: ChangeTitleDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangeTitleDialogFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitBtn.setOnClickListener {
            val orgInput = binding.organizationInput.text.toString()
            val repoInput = binding.repositoryInput.text.toString()

            viewModel.setIssueList(orgInput, repoInput)

            dismiss()
        }
    }
}