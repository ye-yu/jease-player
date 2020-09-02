package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus
import io.github.yeyu.easing.number.KotlinNumberUtil.times

/**
 * A module-specific interpolator.
 * */
@Deprecated("Use functional interpolator")
interface Interpolator<T : Number> {

    /**
     * The bound of the interpolation
     * where `f(0) -> from` and `from != to`
     * */
    val from: T

    /**
     * The bound of the interpolation
     * where `f(1) -> to` and `from != to`
     * */
    val to: T

    /**
     * A function to map the domain of `[0, 1]`
     * to the expected range of values such that:
     *   - `f(0) -> from`
     *   - `f(1) -> to`
     * */
    val function: Function

    /**
     * An interpolating method
     *
     * @suppress unchecked casting of range and domain.
     * @see io.github.yeyu.easing.number.KotlinNumberUtil
     * @param at a value of domain `[0, 1]`
     * @return the interpolated value
     * */
    fun next(at: Double): T {
        @Suppress("UNCHECKED_CAST")
        return (from + (to - from) * function.f(at)) as T
    }
}