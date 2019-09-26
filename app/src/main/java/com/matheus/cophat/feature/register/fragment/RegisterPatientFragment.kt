package com.matheus.cophat.feature.register.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentRegisterPatientBinding
import com.matheus.cophat.feature.register.viewmodel.RegisterViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPatientFragment : BaseFragment<FragmentRegisterPatientBinding>(){

    private val viewModel: RegisterViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_register_patient
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {

    }
}