package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.Interpolator

interface EaseOut<T: Number> : Ease<T> {
    val easeOutFunction: Function
    val easeOutInterpolator1D: Interpolator<T>
        get() = interpolator
}