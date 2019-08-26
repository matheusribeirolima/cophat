package com.matheus.cophat.feature.intro.fragment

import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentGenerateCodeBinding
import com.matheus.cophat.feature.intro.viewmodel.IntroViewModel
import com.matheus.cophat.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GenerateCodeFragment : BaseFragment<FragmentGenerateCodeBinding>() {

    private val viewModel: IntroViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_generate_code
    }

    override fun initBinding() {
        binding = getBinding()

        binding.btBackCode.setOnClickListener { activity?.onBackPressed() }

        binding.btContinueCode.setOnClickListener { findNavController().navigate(viewModel.chooseNav()) }

        context?.let {
            val adapter = ArrayAdapter.createFromResource(
                it,
                R.array.applicators, android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sApplicatorCode.adapter = adapter
        }
    }
}
