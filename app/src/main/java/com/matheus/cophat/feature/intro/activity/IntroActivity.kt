package com.matheus.cophat.feature.intro.activity

import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityIntroBinding
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroActivity : BaseActivity<ActivityIntroBinding>() {

    private val introViewModel: IntroViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_intro
    }

    override fun getViewModel(): BaseViewModel {
        return introViewModel
    }

    override fun initBinding() {
        binding = getBinding()

    }
}
