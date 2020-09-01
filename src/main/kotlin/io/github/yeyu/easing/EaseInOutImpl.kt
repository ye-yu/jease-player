package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.DoubleToGenericCoordinateInterpolator2D
import io.github.yeyu.easing.interpolator.Interpolator
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus
import io.github.yeyu.easing.number.KotlinNumberUtil.plus

/**
 * An ease-inout function with supplied interpolation function
 * */
class EaseInOutImpl<T: Number>(
    from: T,
    to: T,
    override val easeOutFunction: Function,
    override val easeInFunction: Function
    ) : EaseInOut<T> {

    @Suppress("UNCHECKED_CAST")
    private val half: T = (from + (to - from) / 2) as T
    override val easeInInterpolator1D: Interpolator<T> = DoubleToGenericCoordinateInterpolator2D(0.0, from, 0.5, half, easeInFunction)
    override val easeOutInterpolator1D: Interpolator<T> = DoubleToGenericCoordinateInterpolator2D(0.5, half, 1.0, to, easeOutFunction)
    override fun next(at: Double): T = if (at < 0.5) easeInInterpolator1D.next(at)
        else easeOutInterpolator1D.next(at)
}