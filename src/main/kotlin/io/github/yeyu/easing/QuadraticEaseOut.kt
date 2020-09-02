package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * A quadratic ease-out function
 * */
@Deprecated("Use Ease with Linear function")
class QuadraticEaseOut<T : Number>(from: T, to: T) {
    val easeOutFunction: Function = InverseQuadratic
    val interpolator: Interpolator<T> = GenericInterpolator1D(from, to, easeOutFunction)
    fun next(at: Double): T = interpolator.next(at)
}