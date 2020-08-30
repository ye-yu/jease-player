package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.GenericInterpolator1D

/**
 * A linear ease function
 * */
class NoEase1D<T: Number>(from: T, to: T) : Ease<T> {
    override val interpolator = GenericInterpolator1D(from, to)
    override fun next(at: Double): T = interpolator.next(at)
}