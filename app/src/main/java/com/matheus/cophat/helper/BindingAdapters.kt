package com.matheus.cophat.helper

import android.animation.ObjectAnimator
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

@BindingAdapter(value = ["fadeIn", "alreadyLoaded"], requireAll = false)
fun fadeIn(view: View, delay: Long, alreadyLoaded: MutableLiveData<Boolean>?) {
    view.alpha = 0f

    val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f)
    alpha.startDelay = delay
    if (alreadyLoaded != null) {
        alreadyLoaded.observeForever {
            if (!it) {
                alpha.start()
            }
        }
    } else {
        alpha.start()
    }
}