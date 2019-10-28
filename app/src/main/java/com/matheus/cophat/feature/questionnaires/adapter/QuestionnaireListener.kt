package com.matheus.cophat.feature.questionnaires.adapter

import com.matheus.cophat.data.presenter.ItemQuestionnairePresenter

interface QuestionnaireListener {

    fun onClickChildren(item: ItemQuestionnairePresenter)
    fun onClickParents(item: ItemQuestionnairePresenter)
    fun onClickExcel(item: ItemQuestionnairePresenter)
}