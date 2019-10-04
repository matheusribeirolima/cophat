package com.matheus.cophat.feature.questions.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentQuestionsBinding
import com.matheus.cophat.feature.questions.viewmodel.QuestionsViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>() {

    private val viewModel: QuestionsViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_questions
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {

    }
}