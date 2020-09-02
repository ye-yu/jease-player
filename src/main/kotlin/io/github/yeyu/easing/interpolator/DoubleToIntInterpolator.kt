package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import kotlin.math.roundToInt

object DoubleToIntInterpolator : GenericInterpolator<Double, Int> {
    override fun interpolateNext(lowerBound: Int, upperBound: Int, function: Function, at: Double): Int {
        return (lowerBound.toDouble() + (upperBound - lowerBound).toDouble() * function.f(at)).roundToInt()
    }

    override fun interpolateCoordinate(
        x1: Double,
        x2: Double,
        y1: Int,
        y2: Int,
        function: Function,
        at: Double
    ): Int {
        val rate = (at - x1) / (x2 - x1)
        return (y1 + (y2 - y1) * function.f(rate)).roundToInt()
    }
}
