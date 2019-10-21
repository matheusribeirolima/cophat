package com.matheus.cophat.feature.questions.adapter

import com.matheus.cophat.data.local.entity.SubAnswer

interface SubQuestionListener {

    fun onSubAnswerChanged(item: SubAnswer, position: Int)
}