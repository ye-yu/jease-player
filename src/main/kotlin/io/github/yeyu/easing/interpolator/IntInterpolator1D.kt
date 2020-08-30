package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction
import kotlin.math.abs
import kotlin.math.round

class IntInterpolator1D(private val from: Int, private val to: Int, override val function: Function = LinearFunction): Interpolator<Int, Float> {
    override fun next(at: Float): Int {
        return from + round(abs(from - to).toDouble() * function.f(at.toDouble())).toInt()
    }
}
