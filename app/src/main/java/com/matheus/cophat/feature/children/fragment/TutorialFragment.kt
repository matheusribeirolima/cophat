package com.matheus.cophat.feature.children.fragment

import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.databinding.FragmentTutorialBinding
import com.matheus.cophat.feature.children.viewmodel.ChildrenViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_children.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

    private val childrenViewModel: ChildrenViewModel by sharedViewModel()

    override fun getLayout(): Int {
        return R.layout.fragment_tutorial
    }

    override fun initBinding() {
        binding = getBinding()

        binding.btContinueTutorial.setOnClickListener {
            //findNavController().navigate(R.id.)
        }

        binding.btBackTutorial.setOnClickListener { activity?.onBackPressed() }
    }
}
