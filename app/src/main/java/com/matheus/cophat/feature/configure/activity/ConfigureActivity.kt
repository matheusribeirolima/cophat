package com.matheus.cophat.feature.configure.activity

import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityConfigureBinding
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigureActivity : BaseActivity<ActivityConfigureBinding>() {

    private val viewModel: ConfigureViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_configure
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {

    }
}
