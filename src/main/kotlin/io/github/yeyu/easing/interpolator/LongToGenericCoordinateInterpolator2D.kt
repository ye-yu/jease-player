package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction

/**
 * An interpolator that removes the domain constraint of `[0, 1]`
 * and replace with [x1, x2] where x1 < x2.
 * */
@Deprecated("Alpha test")
class LongToGenericCoordinateInterpolator2D<T : Number>(
        private val x1: Long,
        override val from: T,
        private val x2: Long,
        override val to: T,
        override val function: Function = LinearFunction) : Interpolator<T> {

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
    override fun next(at: Double): T = throw NotImplementedError("Perform checking and use next(Long) is object is instance of this class")

    fun next(at: Long): T {
        val rate = (at - x1).toDouble() / (x2 - x1)
        return super.next(rate)
    }
}