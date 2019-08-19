package com.matheus.cophat.feature.questionnaires.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentListQuestionnairesBinding
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListQuestionnairesFragment : BaseFragment<FragmentListQuestionnairesBinding>() {

    private val viewModel: QuestionnairesViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_list_questionnaires
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        binding = getBinding()
    }
}
