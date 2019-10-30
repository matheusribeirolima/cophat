package com.matheus.cophat.feature.configure.fragment

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemAdminPresenter
import com.matheus.cophat.databinding.FragmentAdminBinding
import com.matheus.cophat.feature.configure.adapter.AdminListener
import com.matheus.cophat.feature.configure.adapter.AdminRecyclerAdapter
import com.matheus.cophat.feature.configure.viewmodel.ConfigureViewModel
import com.matheus.cophat.ui.BaseFragment
import com.matheus.cophat.ui.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminFragment : BaseFragment<FragmentAdminBinding>(), AdminListener {

    private val viewModel: ConfigureViewModel by viewModel()

    private val adapter: AdminRecyclerAdapter by inject()

    override fun getLayout(): Int {
        return R.layout.fragment_admin
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        setViews(
            binding.tvTitleAdmin,
            binding.tvSubtitleAdmin,
            binding.rvAdmin,
            binding.ivAddAdmin
        )

        viewModel.initialize()

        configureObservers()
        configureAdapter()
        configureListener()
    }

    private fun configureAdapter() {
        adapter.adminListener = this
        binding.rvAdmin.adapter = adapter
    }

    private fun configureListener() {
        binding.ivAddAdmin.setOnClickListener {
            findNavController().navigate(
                AdminFragmentDirections.actionAdminFragmentToAdminDialog(
                    viewModel.getAddAdmin()
                )
            )
        }
    }

    private fun configureObservers() {
        viewModel.adminPresenter.observe(this,
            Observer {
                adapter.setItems(it.admins)
            })
    }

    override fun onEdit(item: ItemAdminPresenter) {
        findNavController().navigate(
            AdminFragmentDirections.actionAdminFragmentToAdminDialog(
                viewModel.getEditAdmin(item),
                item.adminFirebaseKey
            )
        )
    }

    override fun onRemove(item: ItemAdminPresenter) {
        findNavController().navigate(
            AdminFragmentDirections.actionAdminFragmentToConfigureExcludeDialog(
                item.adminFirebaseKey
            )
        )
    }
}
