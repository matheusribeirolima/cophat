package com.matheus.cophat.feature.parents.activity

import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityParentsBinding
import com.matheus.cophat.feature.parents.viewmodel.ParentsViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ParentsActivity : BaseActivity<ActivityParentsBinding>() {

    private val viewModel: ParentsViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_parents
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        binding = getBinding()
    }
}
