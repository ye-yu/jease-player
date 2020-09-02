package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function

object LongToDoubleInterpolator : GenericInterpolator<Long, Double> {
    override fun interpolateNext(lowerBound: Double, upperBound: Double, function: Function, at: Long): Double =
        throw IllegalAccessError("Unknown behaviour")

    override fun interpolateCoordinate(
        x1: Long,
        x2: Long,
        y1: Double,
        y2: Double,
        function: Function,
        at: Long
    ): Double {
        val rate = (at - x1).toDouble() / (x2 - x1).toDouble()
        return y1 + (y2 - y1) * function.f(rate)
    }
}
