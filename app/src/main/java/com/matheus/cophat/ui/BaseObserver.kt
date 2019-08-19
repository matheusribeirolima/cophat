package com.matheus.cophat.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.matheus.cophat.ui.base.ErrorDialog
import com.matheus.cophat.ui.base.LoadingDialog
import org.koin.core.KoinComponent
import org.koin.core.inject

class BaseObserver constructor(
    private val baseViewModel: BaseViewModel,
    private val fragmentManager: FragmentManager?
) {

    private val loadingDialog = LoadingDialog.newInstance()
    private val errorDialog = ErrorDialog.newInstance()

    fun observeChanges(owner: LifecycleOwner) {
        baseViewModel.handleLoading.observe(
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
        fragmentManager?.let { loadingDialog.show(it) }
    }

    private fun hideLoading() {
        fragmentManager?.let { loadingDialog.dismiss() }
    }

    private fun handleError(throwable: Throwable) {
        fragmentManager?.let {
            errorDialog.show(it)
            errorDialog.handleThrowable(throwable)
        }
    }
}