package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.number.KotlinNumberUtil.abs
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus
import io.github.yeyu.easing.number.KotlinNumberUtil.times

class GenericInterpolator1D<T : Number>(
        private val from: T,
        private val to: T,
        override val function: Function = LinearFunction) : Interpolator<T, Float> {
    override fun next(at: Float): T {
        @Suppress("UNCHECKED_CAST")
        return (from + abs(to - from) * function.f(at.toDouble())) as T
    }
}

