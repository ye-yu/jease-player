package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.DoubleInterpolator1D

interface Ease {
    val interpolator: DoubleInterpolator1D
    fun next(at: Float): Double
}