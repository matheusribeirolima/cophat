package com.matheus.cophat.feature.configure.adapter

import android.view.View
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.databinding.ItemApplicatorBinding
import com.matheus.cophat.ui.BaseViewHolder

class ApplicatorViewHolder(itemView: View, private val applicatorListener: ApplicatorListener) :
    BaseViewHolder<ItemApplicatorBinding, ItemApplicatorPresenter>(itemView) {

    override fun bind(presenter: ItemApplicatorPresenter, position: Int) {
        binding?.presenter = presenter

        binding?.ivEditItemApplicator?.setOnClickListener {
            applicatorListener.onEdit(presenter)
        }

        binding?.ivRemoveItemApplicator?.setOnClickListener {
            applicatorListener.onRemove(presenter)
        }
    }
}