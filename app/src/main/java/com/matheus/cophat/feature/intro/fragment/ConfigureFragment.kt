package com.matheus.cophat.feature.intro.fragment

import android.view.View
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.databinding.FragmentConfigureBinding
import com.matheus.cophat.feature.intro.adapter.ApplicatorRecyclerAdapter
import com.matheus.cophat.ui.BaseFragment

class ConfigureFragment : BaseFragment<FragmentConfigureBinding>() {

    private val adapter = ApplicatorRecyclerAdapter()

    override fun getLayout(): Int {
        return R.layout.fragment_configure
    }

    override fun initBinding() {
        val list = ArrayList<ItemApplicatorPresenter>()
        list.add(ItemApplicatorPresenter("Matheus Ribeiro Lima", View.VISIBLE))
        list.add(ItemApplicatorPresenter("Maria Vidigal", View.VISIBLE))
        list.add(ItemApplicatorPresenter("João da Silva", View.VISIBLE))
        list.add(ItemApplicatorPresenter("Batman Freitas Araújo Not To Do", View.GONE))

        adapter.setItems(list)

        binding.rvConfigure.adapter = adapter

        binding.ivAddConfigure.setOnClickListener {  }
    }

}
