package com.matheus.cophat.feature.intro.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentBeginBinding
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BeginFragment : BaseFragment<FragmentBeginBinding>() {

    private val introViewModel: IntroViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_begin
    }

    override fun getViewModel(): BaseViewModel {
        return introViewModel
    }

    override fun initBinding() {
        binding = getBinding()

        binding.btTest.setOnClickListener { introViewModel.testSave() }
    }
}
