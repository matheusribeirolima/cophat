package com.matheus.cophat.helper

import android.animation.ObjectAnimator
import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import android.R
import android.widget.ArrayAdapter



@BindingAdapter("fadeIn")
fun fadeIn(view: View,
           delay: Long) {
    view.alpha = 0f

    val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
    alpha.startDelay = delay
    alpha.duration = 400
    alpha.start()
}

@BindingAdapter("fadeOut")
fun fadeOut(view: View,
            delay: Long) {
    view.alpha = 1f

    val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f)
    alpha.startDelay = delay
    alpha.duration = 400
    alpha.start()
}

//@BindingAdapter("entries")
//fun Spinner.setEntries(entries: List<Any>?) {
//    val adapter = ArrayAdapter.createFromResource(
//        this,
//        R.array.planets_array, android.R.layout.simple_spinner_item
//    )
//// Specify the layout to use when the list of choices appears
//    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//// Apply the adapter to the spinner
//    spinner.adapter = adapter
//}