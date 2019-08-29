package com.matheus.cophat.feature.intro.fragment

import androidx.lifecycle.Observer
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentConfigureBinding
import com.matheus.cophat.feature.intro.adapter.ApplicatorRecyclerAdapter
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigureFragment : BaseFragment<FragmentConfigureBinding>() {

    private val viewModel: IntroViewModel by viewModel()

    private val adapter: ApplicatorRecyclerAdapter by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_configure
    }

    override fun initBinding() {
        viewModel.initializeConfigure()

        binding.rvConfigure.adapter = adapter

        viewModel.applicatorsPresenter.observe(this, Observer { adapter.setItems(it) })

        binding.ivAddConfigure.setOnClickListener { }
    }

}
