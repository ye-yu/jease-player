package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.Interpolator

interface Ease<T: Number> {
    val interpolator: Interpolator<T, Float>
    fun next(at: Float): T
}