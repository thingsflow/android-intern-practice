package com.thingsflow.internapplication.base.architecture.base.viewbinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.thingsflow.internapplication.base.architecture.base.BaseViewModel
import com.thingsflow.internapplication.base.architecture.base.Cleaner

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding> : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val isInitializedBinding: Boolean
        get() = _binding != null

    protected abstract val viewModel: VM

    protected val cleaner: Cleaner
        get() = viewModel

    protected abstract val backFunc: (() -> Unit)?

    open val onBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            backFunc?.invoke()
        }
    }

    protected abstract fun loadData()
    protected abstract fun setupUi()
    protected abstract fun observeUi()
    protected abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().lifecycleScope.launchWhenCreated {
            observeUi()
        }

        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (backFunc != null) {
            requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressed)
        }
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressed.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cleaner.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        loadData()
    }
}
