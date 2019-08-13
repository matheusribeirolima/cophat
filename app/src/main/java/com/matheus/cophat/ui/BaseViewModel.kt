package com.matheus.cophat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val handleLoading = MutableLiveData<Boolean>()
    val handlePermission = MutableLiveData<Boolean>()
    val handleError = MutableLiveData<Throwable>()

}