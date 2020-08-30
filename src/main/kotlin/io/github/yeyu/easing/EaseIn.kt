package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.Interpolator

interface EaseIn<T: Number>: Ease<T> {
    val easeInFunction: Function
    val easeInInterpolator1D: Interpolator<T>
        get() = interpolator
}