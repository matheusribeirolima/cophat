package com.matheus.cophat.feature.children.fragment

import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentTutorialBinding
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

    private val childrenViewModel: ChildrenViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_tutorial
    }

    override fun getViewModel(): BaseViewModel {
        return childrenViewModel
    }

    override fun initBinding() {
        binding = getBinding()
    }
}
