package com.matheus.cophat.data.presenter

import androidx.annotation.DrawableRes

data class BeginPresenter(
    @DrawableRes
    val beginImage: Int,
    val beginTitle: String,
    val beginSubtitle: String,
    val beginButton: String
)