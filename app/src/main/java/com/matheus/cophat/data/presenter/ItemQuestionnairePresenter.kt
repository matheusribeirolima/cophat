package com.matheus.cophat.data.presenter

import androidx.annotation.DrawableRes

data class ItemQuestionnairePresenter(
    val applicationId: String,
    @DrawableRes
    val childrenDrawable: Int,
    val childrenState: String,
    val childrenEnabled: Boolean,
    val parentsState: String,
    val parentsEnabled: Boolean,
    val applicationTime: String,
    val hospital: String,
    val admin: String,
    val excelEnabled: Boolean
)