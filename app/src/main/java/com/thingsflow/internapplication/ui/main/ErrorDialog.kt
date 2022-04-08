package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.thingsflow.internapplication.databinding.ErrorDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorDialog: DialogFragment(){

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: ErrorDialogFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ErrorDialogFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.errorGuide.text = "존재하지 않는 Repository 입니다."
        binding.errorMessage.text = viewModel.errorMsg.value

        binding.dismissBtn.setOnClickListener {
            dismiss()
        }
    }
}