package io.github.yeyu.easing.function

import kotlin.math.PI
import kotlin.math.sin

/**
 * Sine function where f(0) = sin(-0.5 rad * PI) + 1 = 0
 * and f(1) = sin(0 rad) + 1 = 1
 * */
object SineUpwardFunction : Function {
    override fun f(x: Double): Double = sin((x - 1) * PI * 0.5) + 1
}
