package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.type.Color4C

object DoubleToColor4CInterpolator : GenericInterpolator<Double, Color4C> {
    override fun interpolateNext(lowerBound: Color4C, upperBound: Color4C, function: Function, at: Double): Color4C {
        return lowerBound + (upperBound - lowerBound) * function.f(at)
    }

    override fun interpolateCoordinate(
        x1: Double,
        x2: Double,
        y1: Color4C,
        y2: Color4C,
        function: Function,
        at: Double
    ): Color4C {
        val rate = (at - x1) / (x2 - x1)
        return y1 + (y2 - y1) * function.f(rate)
    }
}
