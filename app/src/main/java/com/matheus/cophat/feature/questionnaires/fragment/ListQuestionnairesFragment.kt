package com.matheus.cophat.feature.questionnaires.fragment

import androidx.lifecycle.Observer
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemQuestionnairePresenter
import com.matheus.cophat.databinding.FragmentListQuestionnairesBinding
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireListener
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireRecyclerAdapter
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListQuestionnairesFragment : BaseFragment<FragmentListQuestionnairesBinding>(),
    QuestionnaireListener {

    private val viewModel: QuestionnairesViewModel by viewModel()
    private val adapter: QuestionnaireRecyclerAdapter by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_list_questionnaires
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        setViews(
            binding.tvTitleQuestionnaires,
            binding.tvSubtitleQuestionnaires,
            binding.rvQuestionnaires
        )
        viewModel.initialize()

        configureAdapter()
        configureObservers()
    }

    private fun configureAdapter() {
        adapter.questionnaireListener = this
        binding.rvQuestionnaires.adapter = adapter
    }

    private fun configureObservers() {
        viewModel.presenters.observe(this,
            Observer {
                adapter.setItems(it)
            })
    }

    override fun onClickExcel(item: ItemQuestionnairePresenter) {

    }
}
