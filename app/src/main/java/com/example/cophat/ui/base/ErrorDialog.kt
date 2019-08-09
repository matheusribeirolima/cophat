package com.example.cophat.ui.base

import androidx.lifecycle.Observer
import com.example.cophat.R
import com.example.cophat.databinding.DialogErrorBinding
import com.example.cophat.ui.BaseDialog
import com.example.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ErrorDialog : BaseDialog<DialogErrorBinding>() {

    companion object {
        private const val TAG = "error_loading"

        @Synchronized
        fun newInstance(): ErrorDialog {
            return ErrorDialog()
        }
    }

    private val errorViewModel: ErrorViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.dialog_error
    }

    override fun getViewModel(): BaseViewModel {
        return errorViewModel
    }

    override fun getDialogTag(): String {
        return TAG
    }

    override fun initBinding() {
        binding = getBinding()

        errorViewModel.errorMessage.observe(this,
            Observer { binding.tvMessageError.text = resources.getText(it) })
    }

    fun handleThrowable(throwable: Throwable?) {
        throwable?.let { errorViewModel.handleThrowable(it) }
    }
}