package com.matheus.cophat.ui

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.matheus.cophat.ui.base.dialog.ErrorDialog
import com.matheus.cophat.ui.base.dialog.LoadingDialog

class BaseObserver constructor(
    private val baseViewModel: BaseViewModel,
    private val fragmentManager: FragmentManager?
) {

    fun observeChanges(owner: LifecycleOwner) {
        baseViewModel.isLoading.observe(
            owner,
            Observer { shouldLoading ->
                if (shouldLoading) {
                    showLoading()
                } else {
                    hideLoading()
                }
            })

        baseViewModel.handleError.observe(owner, Observer { handleError(it) })
    }

    private fun showLoading() {
        fragmentManager?.let { LoadingDialog.newInstance().show(it) }
    }

    private fun hideLoading() {
        fragmentManager?.findFragmentByTag(LoadingDialog.TAG)
            ?.let { (it as DialogFragment).dismiss() }
    }

    private fun handleError(throwable: Throwable) {
        fragmentManager?.let {
            val errorDialog = ErrorDialog.newInstance()
            errorDialog.show(it)
            errorDialog.handleThrowable(throwable)
        }
    }
}