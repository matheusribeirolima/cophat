package com.matheus.cophat.ui.base

import androidx.lifecycle.MutableLiveData
import com.matheus.cophat.R
import com.matheus.cophat.data.model.service.Unexpected
import com.matheus.cophat.ui.BaseViewModel

class ErrorViewModel : BaseViewModel() {

    val errorMessage = MutableLiveData<Int>()

    fun handleThrowable(throwable: Throwable) {
        if (throwable is Unexpected) {
            errorMessage.value = R.string.unexpected_error
        }
    }
}