package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.InverseQuadratic
import io.github.yeyu.easing.function.QuadraticFunction
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.Interpolator
import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.minus

class QuadraticEaseInOut<T: Number>(from: T, to: T): EaseInOut<T> {
    @Suppress("UNCHECKED_CAST")
    private val half: T = ((from - to) / 2) as T
    override val easeOutFunction: Function = InverseQuadratic
    override val easeInFunction: Function = QuadraticFunction
    override val easeInInterpolator1D: Interpolator<T, Float> = GenericInterpolator1D(from, half, easeInFunction)
    override val easeOutInterpolator1D: Interpolator<T, Float> = GenericInterpolator1D(half, to, easeOutFunction)

    override fun next(at: Float): T = if (at < 0.5) easeInInterpolator1D.next(at * 2) else easeOutInterpolator1D.next(at * 2)
}