package io.github.yeyu.easing.function

/**
 * A linear map-to-self function
 * */
object LinearFunction: Function {
    override fun f(x: Double): Double = x
}