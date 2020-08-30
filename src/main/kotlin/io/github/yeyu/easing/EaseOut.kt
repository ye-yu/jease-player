package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.DoubleInterpolator1D

interface EaseOut : Ease {
    val easeOutFunction: Function
    val easeOutInterpolator1D: DoubleInterpolator1D
        get() = interpolator
}