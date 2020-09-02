package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.type.Color3C

object DoubleToColor3CInterpolator : GenericInterpolator<Double, Color3C> {
    override fun interpolateNext(lowerBound: Color3C, upperBound: Color3C, function: Function, at: Double): Color3C {
        return lowerBound + (upperBound - lowerBound) * function.f(at)
    }

    override fun interpolateCoordinate(
        x1: Double,
        x2: Double,
        y1: Color3C,
        y2: Color3C,
        function: Function,
        at: Double
    ): Color3C {
        val rate = (at - x1) / (x2 - x1)
        return y1 + (y2 - y1) * function.f(rate)
    }
}
