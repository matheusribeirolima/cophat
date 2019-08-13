package com.matheus.cophat.ui.base

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseException
import com.matheus.cophat.R
import com.matheus.cophat.ui.BaseViewModel

class ErrorViewModel : BaseViewModel() {

    val errorMessage = MutableLiveData<Int>()

    fun handleThrowable(throwable: Throwable) {
        if (throwable is DatabaseException) {
            errorMessage.value = R.string.database_error
        } else {
            errorMessage.value = R.string.unexpected_error
        }
    }
}