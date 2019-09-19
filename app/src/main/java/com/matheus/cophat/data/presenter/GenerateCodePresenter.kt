package com.matheus.cophat.data.presenter

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.matheus.cophat.data.local.entity.Applicator
import com.matheus.cophat.data.local.entity.Hospital

data class GenerateCodePresenter(
    private var _child: String = "",
    private var _hospital: Hospital = Hospital(),
    private var _applicator: Applicator = Applicator()
) : BaseObservable() {
    @get:Bindable
    var child
        get() = _child
        set(value) {
            _child = value
            notifyPropertyChanged(BR.child)
        }

    @get:Bindable
    var hospital
        get() = _hospital
        set(value) {
            _hospital = value
            notifyPropertyChanged(BR.hospital)
        }

    @get:Bindable
    var applicator
        get() = _applicator
        set(value) {
            _applicator = value
            notifyPropertyChanged(BR.applicator)
        }
}