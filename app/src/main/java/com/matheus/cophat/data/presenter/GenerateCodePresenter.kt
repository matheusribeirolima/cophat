package com.matheus.cophat.data.presenter

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenerateCodePresenter(
    private var _child: String = "",
    private var _hospital: String = "",
    private var _applicator: String = ""
) : Parcelable, BaseObservable() {
    @IgnoredOnParcel
    @get:Bindable
    var child
        get() = _child
        set(value) {
            _child = value
            notifyPropertyChanged(BR.child)
        }

    @IgnoredOnParcel
    @get:Bindable
    var hospital
        get() = _hospital
        set(value) {
            _hospital = value
            notifyPropertyChanged(BR.hospital)
        }

    @IgnoredOnParcel
    @get:Bindable
    var applicator
        get() = _applicator
        set(value) {
            _applicator = value
            notifyPropertyChanged(BR.applicator)
        }
}