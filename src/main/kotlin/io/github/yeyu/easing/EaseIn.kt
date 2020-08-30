package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.DoubleInterpolator1D

interface EaseIn: Ease {
    val easeInFunction: Function
    val easeInInterpolator1D: DoubleInterpolator1D
        get() = interpolator
}