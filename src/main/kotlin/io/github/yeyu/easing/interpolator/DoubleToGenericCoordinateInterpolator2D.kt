package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction

/**
 * An interpolator that removes the domain constraint of `[0, 1]`
 * and replace with [x1, x2] where x1 < x2.
 * */
@Deprecated("Use functional interpolator")
class DoubleToGenericCoordinateInterpolator2D<T : Number>(
    private val x1: Double,
    override val from: T,
    private val x2: Double,
    override val to: T,
    override val function: Function = LinearFunction
) : Interpolator<T> {

    init {
        require(x1 != x2) { "Interpolating domain cannot be the same" }
        require(x1 < x2) { "The interpolating lower bound{$x1} must be less than the upper bound{$x2}" }
    }

    /**
     * An interpolating method
     *
     * @suppress unchecked casting of range and domain.
     * @see io.github.yeyu.easing.number.KotlinNumberUtil
     * @param at a number of domain [x1, x2]
     * @return the interpolated value
     * */
    override fun next(at: Double): T {
        val rate = (at - x1) / (x2 - x1)
        return super.next(rate)
    }
}