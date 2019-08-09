package com.example.cophat.ui

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
}