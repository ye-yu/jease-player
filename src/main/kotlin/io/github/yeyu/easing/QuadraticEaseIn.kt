package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.QuadraticFunction
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

class QuadraticEaseIn<T: Number>(from: T, to: T) : EaseIn<T> {
    override val easeInFunction: Function = QuadraticFunction
    override val interpolator: Interpolator<T, Float> = GenericInterpolator1D(from, to, easeInFunction)
    override fun next(at: Float): T = interpolator.next(at)
}