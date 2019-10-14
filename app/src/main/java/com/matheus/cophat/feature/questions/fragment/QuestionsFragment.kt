package com.matheus.cophat.feature.questions.fragment

import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.AnswerType
import com.matheus.cophat.databinding.FragmentQuestionsBinding
import com.matheus.cophat.feature.questions.viewmodel.QuestionsViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsListener
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
        setViews(
            binding.tvCodeQuestions,
            binding.tvStateQuestions,
            binding.pbStatusQuestions,
            binding.tvStatementQuestions,
            binding.rgAnswerQuestions,
            binding.ivQuestions,
            binding.bbvQuestions
        )
        binding.presenter = viewModel.presenter

        configureListeners()
        configureObservers()

        viewModel.initialize()
    }

    private fun configureListeners() {
        binding.rgAnswerQuestions.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbAlwaysQuestions.id -> {
                    viewModel.presenter.thermometer = R.drawable.ic_thermometer1
                    viewModel.presenter.answer = AnswerType.ALWAYS
                }
                binding.rbOftenQuestions.id -> {
                    viewModel.presenter.thermometer = R.drawable.ic_thermometer2
                    viewModel.presenter.answer = AnswerType.OFTEN
                }
                binding.rbSometimesQuestions.id -> {
                    viewModel.presenter.thermometer = R.drawable.ic_thermometer3
                    viewModel.presenter.answer = AnswerType.SOMETIMES
                }
                binding.rbAlmostNeverQuestions.id -> {
                    viewModel.presenter.thermometer = R.drawable.ic_thermometer4
                    viewModel.presenter.answer = AnswerType.ALMOST_NEVER
                }
                binding.rbNeverQuestions.id -> {
                    viewModel.presenter.thermometer = R.drawable.ic_thermometer5
                    viewModel.presenter.answer = AnswerType.NEVER
                }
            }
        }

        binding.bbvQuestions.setBottomButtonsListener(object : BottomButtonsListener {
            override fun onPrimaryClick() {
                viewModel.updateApplication()
            }

            override fun onSecondaryClick() {
                activity?.onBackPressed()
            }
        })
    }

    private fun configureObservers() {

    }
}