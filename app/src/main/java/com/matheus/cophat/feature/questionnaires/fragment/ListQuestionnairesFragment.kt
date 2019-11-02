package com.matheus.cophat.feature.questionnaires.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import com.firebase.ui.database.paging.DatabasePagingOptions
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter
import com.firebase.ui.database.paging.LoadingState
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.Questionnaire
import com.matheus.cophat.data.presenter.ItemQuestionnairePresenter
import com.matheus.cophat.databinding.FragmentListQuestionnairesBinding
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireListener
import com.matheus.cophat.feature.questionnaires.adapter.QuestionnaireViewHolder
import com.matheus.cophat.feature.questionnaires.viewmodel.QuestionnairesViewModel
import com.matheus.cophat.helper.showToast
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListQuestionnairesFragment : BaseFragment<FragmentListQuestionnairesBinding>(),
    QuestionnaireListener {

    private val viewModel: QuestionnairesViewModel by viewModel()
    private val config: PagedList.Config by inject()
    lateinit var options: DatabasePagingOptions<Questionnaire>
    lateinit var adapter: FirebaseRecyclerPagingAdapter<Questionnaire, QuestionnaireViewHolder>

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

        configureAdapter()
        configureObservers()
    }

    private fun configureAdapter() {
        options = DatabasePagingOptions.Builder<Questionnaire>()
            .setLifecycleOwner(this)
            .setQuery(viewModel.getQuery(), config, Questionnaire::class.java)
            .build()

        adapter = object :
            FirebaseRecyclerPagingAdapter<Questionnaire, QuestionnaireViewHolder>(options) {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): QuestionnaireViewHolder {
                return QuestionnaireViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_questionnaire,
                        parent,
                        false
                    ), this@ListQuestionnairesFragment
                )
            }

            override fun onBindViewHolder(
                holder: QuestionnaireViewHolder,
                position: Int,
                model: Questionnaire
            ) {
                holder.bind(viewModel.convertToPresenter(model), position)
            }

            override fun onLoadingStateChanged(state: LoadingState) {
                when (state) {
                    LoadingState.LOADING_INITIAL, LoadingState.LOADING_MORE ->
                        viewModel.isLoading.postValue(true)
                    LoadingState.LOADED ->
                        viewModel.isLoading.postValue(false)
                    LoadingState.FINISHED -> {
                        viewModel.isLoading.postValue(false)
                        context?.showToast("Todos os questionários foram carregados")
                    }
                    LoadingState.ERROR -> retry()
                }
            }
        }

        binding.rvQuestionnaires.adapter = adapter
    }

    private fun configureObservers() {

    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onClickExcel(item: ItemQuestionnairePresenter) {

    }
}
