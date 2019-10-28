package com.matheus.cophat.feature.questionnaires.adapter

import android.view.View
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemQuestionnairePresenter
import com.matheus.cophat.ui.BaseRecyclerView

class QuestionnaireRecyclerAdapter :
    BaseRecyclerView<ItemQuestionnairePresenter, QuestionnaireViewHolder>() {

    lateinit var questionnaireListener: QuestionnaireListener

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_questionnaire
    }

    override fun getViewHolderInstance(itemView: View, viewType: Int): QuestionnaireViewHolder {
        return QuestionnaireViewHolder(itemView, questionnaireListener)
    }

    override fun onBindViewHolder(
        holder: QuestionnaireViewHolder,
        item: ItemQuestionnairePresenter,
        position: Int
    ) {
        holder.bind(item, position)
    }
}