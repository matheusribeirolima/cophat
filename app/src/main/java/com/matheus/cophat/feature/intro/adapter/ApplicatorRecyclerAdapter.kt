package com.matheus.cophat.feature.intro.adapter

import android.view.View
import com.matheus.cophat.R
import com.matheus.cophat.data.presenter.ItemApplicatorPresenter
import com.matheus.cophat.ui.BaseRecyclerView

class ApplicatorRecyclerAdapter : BaseRecyclerView<ItemApplicatorPresenter, ApplicatorViewHolder>() {

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_applicator
    }

    override fun getViewHolderInstance(itemView: View, viewType: Int): ApplicatorViewHolder {
        return ApplicatorViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ApplicatorViewHolder,
        item: ItemApplicatorPresenter,
        position: Int
    ) {
        holder.bind(item, position)
    }

    override fun setItems(items: List<ItemApplicatorPresenter>) {
        presenterList.clear()
        presenterList.addAll(items)
        notifyDataSetChanged()
    }
}