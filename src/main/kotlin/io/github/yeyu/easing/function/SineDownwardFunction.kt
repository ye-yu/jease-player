package io.github.yeyu.easing.function

import kotlin.math.PI
import kotlin.math.sin

/**
 * Sine function where f(0) = sin(0 rad) = 1
 * and f(1) = sin(0.5 rad * PI) = 1
 * */
object SineDownwardFunction : Function {
    override fun f(x: Double): Double = sin(x * PI * 0.5)
}
