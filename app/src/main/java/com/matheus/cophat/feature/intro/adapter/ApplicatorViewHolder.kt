package com.matheus.cophat.feature.intro.adapter

import android.view.View
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.databinding.ItemApplicatorBinding
import com.matheus.cophat.helper.showToast
import com.matheus.cophat.ui.BaseViewHolder

class ApplicatorViewHolder(itemView: View) :
    BaseViewHolder<ItemApplicatorBinding, ItemApplicatorPresenter>(itemView) {

    override fun bind(presenter: ItemApplicatorPresenter, position: Int) {
        binding?.presenter = presenter

        binding?.ivEditItemApplicator?.setOnClickListener { itemView.context.showToast("Editar") }

        binding?.ivRemoveItemApplicator?.setOnClickListener { itemView.context.showToast("Remover") }
    }
}