package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

class QuadraticEaseOut<T: Number>(from: T, to: T) : EaseOut<T> {
    override val easeOutFunction: Function = InverseQuadratic
    override val interpolator: Interpolator<T, Float> = GenericInterpolator1D(from, to, easeOutFunction)
    override fun next(at: Float): T = interpolator.next(at)
}