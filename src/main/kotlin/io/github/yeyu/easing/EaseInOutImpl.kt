package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.GenericInterpolator
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus

/**
 * An ease-inout function with supplied interpolation function
 *
 * Note: Provide appropriate Double to <Your Type> interpolator
 * */
class EaseInOutImpl<T : Number>(
    override var from: T,
    override var to: T,
    override val easeOutFunction: Function,
    override val easeInFunction: Function,
    override val interpolator: GenericInterpolator<Double, T>
) : EaseInOut<T> {

    @Suppress("UNCHECKED_CAST")
    private val half: T = (from + (to - from) / 2) as T

    override fun next(at: Double): T =
        if (at < 0.5) interpolator.interpolateCoordinate(0.0, 0.5, from, half, easeInFunction, at)
        else interpolator.interpolateCoordinate(0.5, 1.0, half, to, easeOutFunction, at)
}