package com.example.cophat.ui.base

import androidx.lifecycle.MutableLiveData
import com.example.cophat.R
import com.example.cophat.data.model.service.Unexpected
import com.example.cophat.ui.BaseViewModel

class ErrorViewModel : BaseViewModel() {

    val errorMessage = MutableLiveData<Int>()

    fun handleThrowable(throwable: Throwable) {
        if (throwable is Unexpected) {
            errorMessage.value = R.string.unexpected_error
        }
    }
}