package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.DoubleInterpolator1D

class NoEase1D(from: Double, to: Double) : Ease {
    override val interpolator = DoubleInterpolator1D(from, to)
    override fun next(at: Float): Double = interpolator.next(at)
}