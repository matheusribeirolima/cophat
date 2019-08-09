package com.example.cophat.helper

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.isConnected(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}