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
        setViews(
            binding.tvTitleApplicator,
            binding.tvSubtitleApplicator,
            binding.rvApplicator,
            binding.ivAddApplicator
        )

        viewModel.initialize()

        configureObservers()
        configureAdapter()
        configureListener()
    }

    private fun configureAdapter() {
        adapter.applicatorListener = this
        binding.rvApplicator.adapter = adapter
    }

    private fun configureListener() {
        binding.ivAddApplicator.setOnClickListener {
            findNavController().navigate(
                ApplicatorFragmentDirections.actionApplicatorFragmentToApplicatorDialog(
                    viewModel.getAddApplicator()
                )
            )
        }
    }

    private fun configureObservers() {
        viewModel.applicatorPresenter.observe(this,
            Observer {
                adapter.setItems(it.applicators)
            })
    }

    override fun onEdit(item: ItemApplicatorPresenter) {
        findNavController().navigate(
            ApplicatorFragmentDirections.actionApplicatorFragmentToApplicatorDialog(
                viewModel.getEditApplicator(item),
                item.applicatorFirebaseKey
            )
        )
    }

    override fun onRemove(item: ItemApplicatorPresenter) {
        findNavController().navigate(
            ApplicatorFragmentDirections.actionApplicatorFragmentToConfigureExcludeDialog(
                item.applicatorFirebaseKey
            )
        )
    }
}
