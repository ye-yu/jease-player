package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.GenericInterpolator

/**
 * An ease-out function with supplied interpolation function
 * */
class EaseOutImpl<T : Number>(
    override var from: T,
    override var to: T,
    override val easeOutFunction: Function,
    override val interpolator: GenericInterpolator<Double, T>
) : EaseOut<T> {
    override fun next(at: Double): T = interpolator.interpolateNext(from, to, easeOutFunction, at)
}