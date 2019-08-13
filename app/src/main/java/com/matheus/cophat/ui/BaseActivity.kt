package com.matheus.cophat.ui

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private var permissionsRequestCode = 0

    private lateinit var baseObserver: BaseObserver

    internal lateinit var binding: T

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun getViewModel(): BaseViewModel

    abstract fun initBinding()

    fun getBinding(): T {
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayout())

        baseObserver = BaseObserver(getViewModel(), supportFragmentManager)
        baseObserver.observeChanges(this)

        initBinding()
    }

    fun requestPermission(permission: String) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            getViewModel().handlePermission.postValue(true)
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(permission),
                permissionsRequestCode++
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            permissionsRequestCode -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getViewModel().handlePermission.postValue(true)
                } else {
                    getViewModel().handlePermission.postValue(false)
                }
            }
        }
    }
}