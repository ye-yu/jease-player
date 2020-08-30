package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.Interpolator

interface Ease<T: Number> {
    val interpolator: Interpolator<T>
    fun next(at: Double): T
}