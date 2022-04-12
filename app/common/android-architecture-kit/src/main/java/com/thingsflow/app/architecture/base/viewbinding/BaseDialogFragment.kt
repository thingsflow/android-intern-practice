package com.thingsflow.app.architecture.base.viewbinding

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.viewbinding.ViewBinding
import com.thingsflow.app.architecture.base.BaseViewModel
import com.thingsflow.app.architecture.base.Cleaner
import com.thingsflow.app.architecture.base.fullScreen

abstract class BaseDialogFragment<VM : BaseViewModel, B : ViewBinding> : AppCompatDialogFragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected val isInitializedBinding: Boolean
        get() = _binding != null

    protected abstract val viewModel: VM

    protected val cleaner: Cleaner
        get() = viewModel

    var onDismiss: (() -> Unit)? = null

    protected abstract fun setupUi()
    protected abstract fun observeUi()
    protected abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeUi()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = object : AppCompatDialog(context, theme) {
            override fun onBackPressed() {
                dismiss()
            }
        }

        dialog.fullScreen(true)

        dialog.window?.run {
            setGravity(Gravity.CENTER)
            setBackgroundDrawableResource(android.R.color.transparent)
        }

        dialog.setCanceledOnTouchOutside(true)
        dialog.setOnDismissListener {
            onDismiss?.invoke()
        }
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss?.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cleaner.onDestroyView()
        _binding = null
    }

    fun setOnDismissListener(listener: () -> Unit): BaseDialogFragment<VM, B> {
        onDismiss = listener
        return this
    }
}
