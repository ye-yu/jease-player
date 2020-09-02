package io.github.yeyu.easing.function

/**
 * A quadratic map-to-self-squared function
 * */
object QuadraticFunction : Function {
    override fun f(x: Double): Double = x * x
}