package com.thingsflow.app.architecture.base.databinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.thingsflow.app.architecture.BR
import com.thingsflow.app.architecture.base.BaseViewModel
import com.thingsflow.app.architecture.base.Cleaner

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val isInitializedBinding: Boolean
        get() = _binding != null

    protected abstract val viewModel: VM

    protected val cleaner: Cleaner
        get() = viewModel

    open val onBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            backFragment()
        }
    }

    protected abstract fun setupUi()
    protected abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container).apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressed)
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
    }

    abstract fun backFragment()
}
