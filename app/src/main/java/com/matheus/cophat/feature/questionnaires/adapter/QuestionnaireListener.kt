package com.matheus.cophat.feature.questionnaires.adapter

import com.matheus.cophat.data.presenter.ItemQuestionnairePresenter

interface QuestionnaireListener {

    fun onClickExcel(item: ItemQuestionnairePresenter)
}