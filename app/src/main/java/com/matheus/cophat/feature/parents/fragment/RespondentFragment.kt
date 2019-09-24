package com.matheus.cophat.feature.parents.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentRespondentBinding
import com.matheus.cophat.feature.parents.viewmodel.ParentsViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RespondentFragment : BaseFragment<FragmentRespondentBinding>() {

    private val viewModel: ParentsViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_respondent
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        viewModel.initialize()
    }
}
