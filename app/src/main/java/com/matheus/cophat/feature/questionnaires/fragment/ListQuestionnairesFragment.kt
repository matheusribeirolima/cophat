package com.matheus.cophat.feature.questionnaires.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentListQuestionnairesBinding
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListQuestionnairesFragment : BaseFragment<FragmentListQuestionnairesBinding>() {

    private val viewModel: QuestionnairesViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_list_questionnaires
    }

    override fun initBinding() {
        binding = getBinding()
    }
}
