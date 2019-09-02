package com.matheus.cophat.data.presenter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApplicatorConfigurePresenter(
    var title: String = "",
    var subtitle: String = "",
    var name: String = "",
    var contact: String = ""
) : Parcelable