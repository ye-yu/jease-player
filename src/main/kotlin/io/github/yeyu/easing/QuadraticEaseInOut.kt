package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.function.QuadraticFunction
import io.github.yeyu.easing.interpolator.GenericCoordinateInterpolator2D
import io.github.yeyu.easing.interpolator.Interpolator
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus

/**
 * A quadratic ease-inout function
 *
 * An ease-in is performed on the first half of the range
 * of `from` to `to`, and an ease-out is performed on the
 * second half.
 * */
class QuadraticEaseInOut<T : Number>(from: T, to: T) : EaseInOut<T> {
    @Suppress("UNCHECKED_CAST")
    private val half: T = (from + (to - from) / 2) as T
    override val easeOutFunction: Function = InverseQuadratic
    override val easeInFunction: Function = QuadraticFunction
    override val easeInInterpolator1D: Interpolator<T> = GenericCoordinateInterpolator2D(0.0, from, 0.5, half, easeInFunction)
    override val easeOutInterpolator1D: Interpolator<T> = GenericCoordinateInterpolator2D(0.5, half, 1.0, to, easeOutFunction)

    override fun next(at: Double): T = if (at < 0.5) easeInInterpolator1D.next(at) else easeOutInterpolator1D.next(at)
}