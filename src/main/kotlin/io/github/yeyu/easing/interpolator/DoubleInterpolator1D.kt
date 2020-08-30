package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction

data class DoubleInterpolator1D(
        private val lower: Double,
        private val upper: Double,
        override val function: Function = LinearFunction) : Interpolator<Double> {
    override fun next(at: Double): Double {
        if (at < 0 || at > 1) throw IllegalArgumentException("At must be in the range of [0, 1]")
        return lower + (upper - lower) * function.f(at)
    }
}