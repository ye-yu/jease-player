package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction

data class CoordinateInterpolator2D(
        private val x1: Double,
        private val y1: Double,
        private val x2: Double,
        private val y2: Double,
        override val function: Function = LinearFunction) : Interpolator<Double> {

    init {
        require(x1 != x2) { "Xs cannot be the same" }
    }

    private val rate = (y2 - y1)/(x2 - x1)
    override fun next(at: Double): Double {
        return at * rate
    }
}