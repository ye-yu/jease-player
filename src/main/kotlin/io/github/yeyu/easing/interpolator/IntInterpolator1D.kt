package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction
import kotlin.math.round

@Deprecated("Alpha test", replaceWith = ReplaceWith("GenericInterpolator<Int>", "io.github.yeyu.easing.interpolator.GenericInterpolator"))
class IntInterpolator1D(override val from: Int, override val to: Int, override val function: Function = LinearFunction) : Interpolator<Int> {
    override fun next(at: Double): Int {
        return from + round((to - from).toDouble() * function.f(at)).toInt()
    }
}
