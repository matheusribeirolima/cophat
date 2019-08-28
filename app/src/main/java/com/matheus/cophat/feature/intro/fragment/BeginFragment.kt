package com.matheus.cophat.feature.intro.fragment

import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentBeginBinding
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.helper.showToast
import com.matheus.cophat.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BeginFragment : BaseFragment<FragmentBeginBinding>() {

    private val viewModel: IntroViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_begin
    }

    override fun initBinding() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        configureListeners()

        viewModel.initializeBegin()

        viewModel.beginPresenter.observe(this,
            Observer { binding.presenter = it })
    }

    private fun configureListeners() {
        binding.btFormBegin.setOnClickListener {
            findNavController().navigate(R.id.action_beginFragment_to_generateCodeFragment)
        }

        binding.btListFormsBegin.setOnClickListener {
            findNavController().navigate(R.id.action_beginFragment_to_nav_questionnaires)
        }

        binding.btConfigureBegin.setOnClickListener {
            findNavController().navigate(R.id.action_beginFragment_to_configureFragment)
        }
    }
}
