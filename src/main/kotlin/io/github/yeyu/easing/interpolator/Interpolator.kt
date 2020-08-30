package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function

interface Interpolator<T: Number, I: Number> {
    val function: Function

    fun next(at: I): T
}