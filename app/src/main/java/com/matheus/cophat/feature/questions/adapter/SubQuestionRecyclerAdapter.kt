package com.matheus.cophat.feature.questions.adapter

import android.view.View
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.SubAnswer
import com.matheus.cophat.ui.BaseRecyclerView

class SubQuestionRecyclerAdapter : BaseRecyclerView<SubAnswer, SubQuestionViewHolder>() {

    lateinit var subQuestionListener: SubQuestionListener

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_sub_question
    }

    override fun getViewHolderInstance(itemView: View, viewType: Int): SubQuestionViewHolder {
        return SubQuestionViewHolder(itemView, subQuestionListener)
    }

    override fun onBindViewHolder(
        holder: SubQuestionViewHolder,
        item: SubAnswer,
        position: Int
    ) {
        holder.bind(item, position)
    }

    override fun setItems(items: List<SubAnswer>) {
        presenterList.clear()
        presenterList.addAll(items)
        notifyDataSetChanged()
    }
}