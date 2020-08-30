package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * A quadratic ease-out function
 * */
class QuadraticEaseOut<T: Number>(from: T, to: T) : EaseOut<T> {
    override val easeOutFunction: Function = InverseQuadratic
    override val interpolator: Interpolator<T> = GenericInterpolator1D(from, to, easeOutFunction)
    override fun next(at: Double): T = interpolator.next(at)
}