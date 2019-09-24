package com.matheus.cophat.feature.generate.fragment

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentGenerateCodeBinding
import com.matheus.cophat.feature.generate.viewmodel.GenerateCodeViewModel
import com.matheus.cophat.helper.CustomSpinnerListener
import com.matheus.cophat.helper.OnOnlyItemSelectedListener
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsListener
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateCodeFragment : BaseFragment<FragmentGenerateCodeBinding>() {

    private val viewModel: GenerateCodeViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_generate_code
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        setViews(
            binding.tvTitleCode,
            binding.tvChildCode,
            binding.etChildCode,
            binding.tvHospitalCode,
            binding.sHospitalCode,
            binding.tvApplicatorCode,
            binding.sApplicatorCode,
            binding.bbvCode
        )
        binding.presenter = viewModel.presenter

        binding.presenter?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                viewModel.validatePresenter()
            }
        })

        configureListeners()
        configureObservers()

        viewModel.initialize()
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
        binding.bbvCode.setBottomButtonsListener(object : BottomButtonsListener {
            override fun onPrimaryClick() {
                lifecycleScope.launch {
                    viewModel.initiateQuestionnaire()
                    findNavController().navigate(viewModel.chooseNav())
                }
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
                        binding.presenter?.applicator = applicators[position]
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
                        binding.presenter?.hospital = hospitals[position]
                    }
                })
        })
    }
}
