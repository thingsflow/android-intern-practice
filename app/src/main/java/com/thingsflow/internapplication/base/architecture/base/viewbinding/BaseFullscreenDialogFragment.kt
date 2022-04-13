package com.thingsflow.internapplication.base.architecture.base.viewbinding

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.base.architecture.base.BaseViewModel
import com.thingsflow.internapplication.base.architecture.base.Cleaner

abstract class BaseFullscreenDialogFragment<VM : BaseViewModel, B : ViewBinding> :
    AppCompatDialogFragment() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
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
        }.apply {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
            window?.setGravity(Gravity.CENTER)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.setDimAmount(0.7f)

            window?.attributes?.width = ViewGroup.LayoutParams.MATCH_PARENT
            window?.attributes?.height = ViewGroup.LayoutParams.MATCH_PARENT

            setCanceledOnTouchOutside(false)
            setOnDismissListener {
                onDismiss?.invoke()
            }
        }

        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss?.invoke()
    }

    override fun onCancel(dialog: DialogInterface) {
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cleaner.onDestroyView()
        _binding = null
    }

    fun setOnDismissListener(listener: () -> Unit): BaseFullscreenDialogFragment<VM, B> {
        onDismiss = listener
        return this
    }
}
