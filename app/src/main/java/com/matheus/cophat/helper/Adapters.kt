package com.matheus.cophat.helper

import android.animation.ObjectAnimator
import android.view.View
import androidx.databinding.BindingAdapter

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