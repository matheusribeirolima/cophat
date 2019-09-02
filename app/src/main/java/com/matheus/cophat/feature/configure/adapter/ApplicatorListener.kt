package com.matheus.cophat.feature.configure.adapter

import com.matheus.cophat.data.presenter.ItemApplicatorPresenter

interface ApplicatorListener {

    fun onEdit(item: ItemApplicatorPresenter)

    fun onRemove(item: ItemApplicatorPresenter)
}