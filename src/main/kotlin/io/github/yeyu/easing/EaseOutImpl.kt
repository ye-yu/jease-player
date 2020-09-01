package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * An ease-out function with supplied interpolation function
 * */
class EaseOutImpl<T: Number>(
    from: T,
    to: T,
    override val easeOutFunction: Function
    ) : EaseOut<T> {

    override val interpolator: Interpolator<T> = GenericInterpolator1D(from, to, easeOutFunction)
    override fun next(at: Double): T = interpolator.next(at)
}