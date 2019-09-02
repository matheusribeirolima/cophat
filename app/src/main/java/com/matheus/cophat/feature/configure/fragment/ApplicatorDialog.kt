package com.matheus.cophat.feature.configure.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.matheus.cophat.R
import com.matheus.cophat.databinding.DialogApplicatorBinding
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.helper.showToast
import com.matheus.cophat.ui.BaseDialog
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplicatorDialog : BaseDialog<DialogApplicatorBinding>() {

    private val viewModel: ConfigureViewModel by viewModel()

    private val args: ApplicatorDialogArgs by navArgs()

    companion object {
        private const val TAG = "dialog_applicator"
    }

    override fun getLayout(): Int {
        return R.layout.dialog_applicator
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getDialogTag(): String {
        return TAG
    }

    override fun initBinding() {
        isCancelable = false
        binding.presenter = args.presenter

        viewModel.statusApplicator.observe(this, Observer {
            context?.showToast(it)
            dismiss()
            findNavController().navigate(R.id.action_applicatorDialog_to_applicatorFragment)
        })

        configureListeners()
    }

    private fun configureListeners() {
        binding.btCancelApplicator.setOnClickListener {
            dismiss()
        }

        binding.btSaveApplicator.setOnClickListener {
            viewModel.saveOrUpdateApplicator(binding.presenter, args.key)
        }
    }
}