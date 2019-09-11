package com.matheus.cophat.feature.children.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentTutorialBinding
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import com.matheus.cophat.ui.base.view.BottomButtonsListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

    private val viewModel: ChildrenViewModel by viewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_tutorial
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        binding.loading = viewModel.isLoading

        binding.bbvTutorial.setBottomButtonsListener(object :
            BottomButtonsListener {
            override fun onPrimaryClick() {
                //findNavController().navigate(R.id.)
            }

            override fun onSecondaryClick() {
                activity?.onBackPressed()

            }
        })
    }
}
