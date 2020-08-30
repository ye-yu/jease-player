package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus
import io.github.yeyu.easing.number.KotlinNumberUtil.times

data class GenericCoordinateInterpolator2D<T: Number>(
        private val x1: Double,
        private val y1: T,
        private val x2: Double,
        private val y2: T,
        override val function: Function = LinearFunction) : Interpolator<T> {

    init {
        require(x1 != x2) { "Xs cannot be the same" }
    }

    override fun next(at: Double): T {
        val rate = (at - x1) / (x2 - x1)
        @Suppress("UNCHECKED_CAST")
        return (y1 + (y2 - y1) * function.f(rate)) as T
    }
}