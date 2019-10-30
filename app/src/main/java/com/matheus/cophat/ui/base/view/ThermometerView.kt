package com.matheus.cophat.ui.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.matheus.cophat.R
import com.matheus.cophat.data.local.entity.AnswerType
import com.matheus.cophat.databinding.ViewThermometerBinding

class ThermometerView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    lateinit var listener: ThermometerListener

    val binding: ViewThermometerBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.view_thermometer, this, true
        )

    init {
        binding.rgThermometer.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbAlways.id -> {
                    binding.ivThermometer.setImageResource(R.drawable.ic_thermometer1)
                    listener.onAnswerChanged(AnswerType.ALWAYS)
                }
                binding.rbOften.id -> {
                    binding.ivThermometer.setImageResource(R.drawable.ic_thermometer2)
                    listener.onAnswerChanged(AnswerType.OFTEN)
                }
                binding.rbSometimes.id -> {
                    binding.ivThermometer.setImageResource(R.drawable.ic_thermometer3)
                    listener.onAnswerChanged(AnswerType.SOMETIMES)
                }
                binding.rbAlmostNever.id -> {
                    binding.ivThermometer.setImageResource(R.drawable.ic_thermometer4)
                    listener.onAnswerChanged(AnswerType.ALMOST_NEVER)
                }
                binding.rbNever.id -> {
                    binding.ivThermometer.setImageResource(R.drawable.ic_thermometer5)
                    listener.onAnswerChanged(AnswerType.NEVER)
                }
            }
        }
    }

    fun setThermometerListener(listener: ThermometerListener) {
        this.listener = listener
    }
}