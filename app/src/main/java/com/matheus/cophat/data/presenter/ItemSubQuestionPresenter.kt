package com.matheus.cophat.data.presenter

import com.matheus.cophat.data.local.entity.AnswerType
import com.matheus.cophat.data.local.entity.SubAnswerType

data class ItemSubQuestionPresenter(
    var type: SubAnswerType? = null,
    var description: String? = null,
    var other: String? = null,
    var descriptionVisibility: Int? = null,
    var otherVisibility: Int? = null,
    var chosenSubAnswer: AnswerType? = null
)