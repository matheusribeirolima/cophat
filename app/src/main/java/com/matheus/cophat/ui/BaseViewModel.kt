package com.matheus.cophat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheus.cophat.BuildConfig

abstract class BaseViewModel : ViewModel() {

    val handleLoading = MutableLiveData<Boolean>()
    val handlePermission = MutableLiveData<Boolean>()
    val handleError = MutableLiveData<Throwable>()
    val isChildren = BuildConfig.FLAVOR == "children"

    //abstract fun initialize()

}