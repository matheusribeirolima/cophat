package com.matheus.cophat.feature.configure.fragment

import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.matheus.cophat.R
import com.matheus.cophat.databinding.DialogApplicatorBinding
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.helper.showToast
import com.matheus.cophat.ui.BaseDialog
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.android.synthetic.main.dialog_applicator.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplicatorDialog : BaseDialog<DialogApplicatorBinding>() {

    private val viewModel: ConfigureViewModel by viewModel()

    private val args: ApplicatorDialogArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.dialog_applicator
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun getDialogTag(): String {
        return "dialog_applicator"
    }

    override fun initBinding() {
        isCancelable = false

        binding.presenter = args.presenter

        binding.presenter?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.verifyDialogPresenter(binding.presenter)
            }
        })

        viewModel.dialogValidator.observe(this, Observer {
            binding.buttonPresenter = it
        })

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