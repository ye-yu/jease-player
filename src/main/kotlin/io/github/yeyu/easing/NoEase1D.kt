package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.GenericInterpolator1D

/**
 * A linear ease function
 * */
@Deprecated("Use Ease with Linear function")
class NoEase1D<T : Number>(from: T, to: T) {
    val interpolator = GenericInterpolator1D(from, to)
    fun next(at: Double): T = interpolator.next(at)
}