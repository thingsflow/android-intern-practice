package com.thingsflow.internapplication.base.architecture.base.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.thingsflow.internapplication.BR
import com.thingsflow.internapplication.base.architecture.base.BaseViewModel
import com.thingsflow.internapplication.base.architecture.base.Cleaner

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val isInitializedBinding: Boolean
        get() = _binding != null

    abstract val viewModel: VM

    protected val cleaner: Cleaner
        get() = viewModel

    protected abstract fun setupUi()
    protected abstract fun createBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = createBinding().apply {
            setVariable(BR.viewModel, viewModel)
            lifecycleOwner = this@BaseActivity
        }
        setContentView(binding.root)

        setupUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleaner.onDestroyView()
    }
}
