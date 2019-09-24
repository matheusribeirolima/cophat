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
import com.matheus.cophat.ui.base.view.BottomButtonsListener
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

        setViews(
            binding.tvTitleDialogApplicator,
            binding.tvSubtitleDialogApplicator,
            binding.tvNameDialogApplicator,
            binding.etNameDialogApplicator,
            binding.tvContactDialogApplicator,
            binding.etContactApplicator,
            binding.bbvApplicator
        )

        binding.presenter = args.presenter

        binding.presenter?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.verifyDialogPresenter(binding.presenter)
            }
        })

        viewModel.isButtonEnabled.observe(this, Observer {
            binding.bbvApplicator.updatePrimaryButton(it)
        })

        viewModel.statusApplicator.observe(this, Observer {
            context?.showToast(it)
            dismiss()
            findNavController().navigate(R.id.action_applicatorDialog_to_applicatorFragment)
        })

        configureListeners()
    }

    private fun configureListeners() {
        binding.bbvApplicator.setBottomButtonsListener(object :
            BottomButtonsListener {
            override fun onPrimaryClick() {
                viewModel.saveOrUpdateApplicator(binding.presenter, args.key)
            }

            override fun onSecondaryClick() {
                dismiss()
            }
        })
    }
}