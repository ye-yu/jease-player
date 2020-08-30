package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function

interface Interpolator<T: Number> {
    val function: Function

    fun next(at: Double): T
}