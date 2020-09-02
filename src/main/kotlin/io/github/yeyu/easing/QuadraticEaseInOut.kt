package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.function.QuadraticFunction
import io.github.yeyu.easing.interpolator.DoubleToGenericCoordinateInterpolator2D
import io.github.yeyu.easing.interpolator.Interpolator
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus

/**
 * A quadratic ease-inout function
 *
 * An ease-in is performed on the first half of the range,
 * and an ease-out is performed on the
 * second half.
 * */
@Deprecated("Use Ease with Linear function")
class QuadraticEaseInOut<T : Number>(from: T, to: T) {
    @Suppress("UNCHECKED_CAST")
    private val half: T = (from + (to - from) / 2) as T
    val easeOutFunction: Function = InverseQuadratic
    val easeInFunction: Function = QuadraticFunction
    val easeInInterpolator1D: Interpolator<T> =
        DoubleToGenericCoordinateInterpolator2D(0.0, from, 0.5, half, easeInFunction)
    val easeOutInterpolator1D: Interpolator<T> =
        DoubleToGenericCoordinateInterpolator2D(0.5, half, 1.0, to, easeOutFunction)

    fun next(at: Double): T = if (at < 0.5) easeInInterpolator1D.next(at) else easeOutInterpolator1D.next(at)
}