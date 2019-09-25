package com.matheus.cophat.feature.register.activity

import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityRegisterBinding
import com.matheus.cophat.feature.register.viewmodel.RegisterViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {

    }
}