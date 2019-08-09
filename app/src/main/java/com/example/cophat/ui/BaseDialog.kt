package com.example.cophat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialog<T : ViewDataBinding> : DialogFragment() {

    private lateinit var baseObserver: BaseObserver
    internal lateinit var binding: T

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getViewModel(): BaseViewModel

    abstract fun getDialogTag(): String

    abstract fun initBinding()

    fun getBinding(): T {
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseObserver = BaseObserver(getViewModel(), fragmentManager)
        baseObserver.observeChanges(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, getDialogTag())
    }
}