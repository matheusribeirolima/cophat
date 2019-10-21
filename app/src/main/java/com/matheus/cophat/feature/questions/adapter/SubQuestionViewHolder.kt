package com.matheus.cophat.feature.questions.adapter

import android.view.View
import com.matheus.cophat.data.local.entity.AnswerType
import com.matheus.cophat.data.local.entity.SubAnswer
import com.matheus.cophat.databinding.ItemSubQuestionBinding
import com.matheus.cophat.ui.BaseViewHolder

class SubQuestionViewHolder(itemView: View, private val subQuestionListener: SubQuestionListener) :
    BaseViewHolder<ItemSubQuestionBinding, SubAnswer>(itemView) {

    override fun bind(presenter: SubAnswer, position: Int) {
        //if open mostrar edittext
        binding?.tvAlternative?.text = presenter.type?.chosenSubAnswer

        binding?.rgThermometerAlternative?.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbAlwaysAlternative.id -> {
                    presenter.chosenSubAnswer = AnswerType.ALWAYS
                }
                binding.rbOftenAlternative.id -> {
                    presenter.chosenSubAnswer = AnswerType.OFTEN
                }
                binding.rbSometimesAlternative.id -> {
                    presenter.chosenSubAnswer = AnswerType.SOMETIMES
                }
                binding.rbAlmostNeverAlternative.id -> {
                    presenter.chosenSubAnswer = AnswerType.ALMOST_NEVER
                }
                binding.rbNeverAlternative.id -> {
                    presenter.chosenSubAnswer = AnswerType.NEVER
                }
            }
            subQuestionListener.onSubAnswerChanged(presenter, position)
        }
    }
}