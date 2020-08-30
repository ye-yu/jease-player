package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction
import java.lang.IllegalArgumentException
import kotlin.math.abs

data class DoubleInterpolator1D(
        private val lower: Double,
        private val upper: Double,
        override val function: Function = LinearFunction) : Interpolator<Double, Float> {
    override fun next(at: Float): Double {
        if (at < 0 || at > 1) throw IllegalArgumentException("At must be in the range of [0, 1]")
        return lower + abs(upper - lower) * function.f(at.toDouble())
    }
}