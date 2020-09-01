package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * An ease-in function with supplied interpolation function
 * */
class EaseInImpl<T: Number>(
    from: T,
    to: T,
    override val easeInFunction: Function
    ) : EaseIn<T> {

    override val interpolator: Interpolator<T> = GenericInterpolator1D(from, to, easeInFunction)
    override fun next(at: Double): T = interpolator.next(at)
}