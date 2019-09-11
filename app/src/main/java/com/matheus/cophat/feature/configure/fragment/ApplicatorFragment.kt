package com.matheus.cophat.feature.configure.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.databinding.FragmentApplicatorBinding
import com.matheus.cophat.feature.configure.adapter.ApplicatorListener
import com.matheus.cophat.feature.configure.adapter.ApplicatorRecyclerAdapter
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ApplicatorFragment : BaseFragment<FragmentApplicatorBinding>(), ApplicatorListener {

    private val viewModel: ConfigureViewModel by viewModel()

    private val adapter: ApplicatorRecyclerAdapter by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_applicator
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        viewModel.initialize()

        viewModel.applicatorsPresenter.observe(this, Observer { adapter.setItems(it) })

        adapter.applicatorListener = this

        binding.loading = viewModel.isLoading

        binding.rvApplicator.adapter = adapter

        binding.ivAddApplicator.setOnClickListener {
            findNavController().navigate(
                ApplicatorFragmentDirections.actionApplicatorFragmentToApplicatorDialog(
                    viewModel.getAddApplicator()
                )
            )
        }
    }

    override fun onEdit(item: ItemApplicatorPresenter) {
        findNavController().navigate(
            ApplicatorFragmentDirections.actionApplicatorFragmentToApplicatorDialog(
                viewModel.getEditApplicator(item),
                item.applicatorDatabaseKey
            )
        )
    }

    override fun onRemove(item: ItemApplicatorPresenter) {
        findNavController().navigate(
            ApplicatorFragmentDirections.actionApplicatorFragmentToConfigureExcludeDialog(
                item.applicatorDatabaseKey
            )
        )
    }
}
