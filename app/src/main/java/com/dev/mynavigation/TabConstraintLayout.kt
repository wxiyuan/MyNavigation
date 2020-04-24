package com.dev.mynavigation

import android.animation.ArgbEvaluator
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class TabConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val argbEvaluator = ArgbEvaluator()
    private val normalColor = ContextCompat.getColor(context, android.R.color.black)
    private val selectedColor = ContextCompat.getColor(context, R.color.colorTabSelected)

    fun switchToNormal() {
        setProperties(normalColor, 0f)
    }

    fun switchToSelected() {
        setProperties(selectedColor, 1f)
    }

    fun setFadePercent(percent : Float) {
        val color = argbEvaluator.evaluate(1f - percent, normalColor, selectedColor)
        setProperties(color.toString().toInt(), 1f - percent)
    }

    private fun setProperties(color : Int, alpha : Float) {
        getChildAt(0)?.let {
            if (it is ImageView) {
                it.imageTintList = ColorStateList.valueOf(color)
                if (alpha == 1f) {
                    it.visibility = View.GONE
                } else {
                    it.visibility = View.VISIBLE
                }
            }
        }

        getChildAt(1)?.let {
            if (it is ImageView) {
                it.alpha = alpha
            }
        }

        getChildAt(2)?.let {
            if (it is TextView) {
                it.setTextColor(color)
            }
        }
    }
}