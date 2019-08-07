package com.example.cophat.helper

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class ResourceManager constructor(var context: Context) {

    fun getString(stringRes: Int): String {
        return context.resources.getString(stringRes)
    }

    fun getQuantityString(stringRes: Int, quantity: Int): String {
        return context.resources.getQuantityString(stringRes, quantity, quantity)
    }

    fun getString(stringRes: Int, vararg textToFormat: Any): String {
        return context.resources.getString(stringRes, *textToFormat)
    }

    fun getDrawable(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }

    fun getDimension(dimensionRes: Int): Float {
        return context.resources.getDimension(dimensionRes)
    }

    fun getColor(color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}