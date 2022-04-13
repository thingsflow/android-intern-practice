package com.thingsflow.internapplication.base.architecture.base.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.thingsflow.internapplication.base.architecture.base.BaseViewModel
import com.thingsflow.internapplication.base.architecture.base.Cleaner

abstract class BaseActivity<VM : BaseViewModel, B : ViewBinding> : AppCompatActivity() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val isInitializedBinding: Boolean
        get() = _binding != null

    abstract val viewModel: VM

    protected val cleaner: Cleaner
        get() = viewModel

    protected abstract fun loadData()
    protected abstract fun setupUi()
    protected abstract fun observeUi()
    protected abstract fun createBinding(): B

    protected open fun installSplashScreenOver12() {
        /*
        if os version < 12(sdk 31), need to installSplashScreen.
         */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreenOver12()

        _binding = createBinding()
        setContentView(binding.root)

        setupUi()
        observeUi()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleaner.onDestroyView()
    }
}
