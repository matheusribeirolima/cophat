package com.matheus.cophat.feature.intro.fragment

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentGenerateCodeBinding
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.helper.CustomSpinnerListener
import com.matheus.cophat.helper.OnOnlyItemSelectedListener
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateCodeFragment : BaseFragment<FragmentGenerateCodeBinding>() {

    private val viewModel: IntroViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_generate_code
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        binding.loading = viewModel.isLoading
        binding.presenter = viewModel.generateCodePresenter

        binding.presenter?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.validatePresenter(binding.presenter)
            }
        })

        configureListeners()
        configureObservers()

        viewModel.initializeGenerateCode()
    }

    private fun <T> generateAdapter(context: Context, list: List<T>): ArrayAdapter<T> {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        return adapter
    }

    private fun configureListeners() {
        binding.bbvCode.setBottomButtonsListener(object :
            BottomButtonsListener {
            override fun onPrimaryClick() {
                findNavController().navigate(viewModel.chooseNav())
            }

            override fun onSecondaryClick() {
                activity?.onBackPressed()
            }
        })
    }

    private fun configureObservers() {
        viewModel.isButtonEnabled.observe(this, Observer {
            binding.bbvCode.updatePrimaryButton(it)
        })

        viewModel.applicators.observe(this, Observer { applicators ->
            context?.let { context ->
                binding.sApplicatorCode.adapter = generateAdapter(context, applicators)
            }

            binding.sApplicatorCode.onItemSelectedListener = CustomSpinnerListener(
                object : OnOnlyItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        binding.presenter?.applicator = applicators[position].name
                    }
                })
        })

        viewModel.hospitals.observe(this, Observer { hospitals ->
            context?.let { context ->
                binding.sHospitalCode.adapter = generateAdapter(context, hospitals)
            }

            binding.sHospitalCode.onItemSelectedListener = CustomSpinnerListener(
                object : OnOnlyItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        binding.presenter?.hospital = hospitals[position].name
                    }
                })
        })
    }
}
