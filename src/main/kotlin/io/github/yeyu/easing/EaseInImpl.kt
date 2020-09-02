package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.GenericInterpolator

/**
 * An ease-in function with supplied interpolation function.
 *
 * Note: Use appropriate Double to <Target Type> here
 * */
class EaseInImpl<T : Number>(
    override var from: T,
    override var to: T,
    override val easeInFunction: Function,
    override val interpolator: GenericInterpolator<Double, T>
) : EaseIn<T> {
    override fun next(at: Double): T = interpolator.interpolateNext(from, to, easeInFunction, at)
}