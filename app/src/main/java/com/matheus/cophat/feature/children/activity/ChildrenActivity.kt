package com.matheus.cophat.feature.children.activity

import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityChildrenBinding
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChildrenActivity : BaseActivity<ActivityChildrenBinding>() {

    private val viewModel: ChildrenViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.activity_children
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {



    }
}
