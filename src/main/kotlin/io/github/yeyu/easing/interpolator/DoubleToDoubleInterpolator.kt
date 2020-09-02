package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function

object DoubleToDoubleInterpolator : GenericInterpolator<Double, Double> {
    override fun interpolateNext(lowerBound: Double, upperBound: Double, function: Function, at: Double): Double {
        return lowerBound + (upperBound - lowerBound) * function.f(at)
    }

    override fun interpolateCoordinate(
        x1: Double,
        x2: Double,
        y1: Double,
        y2: Double,
        function: Function,
        at: Double
    ): Double {
        val rate = (at - x1) / (x2 - x1)
        return y1 + (y2 - y1) * function.f(rate)
    }
}
