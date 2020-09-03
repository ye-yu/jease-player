package io.github.yeyu.easing.function

import kotlin.math.pow

/**
 * A utility object to create a custom function on the go
 * */
object FunctionMaker {
    /**
     * A Kotlin native utility function to create a custom function on the go
     * */
    fun createFunction(fn: (Double) -> Double): Function {
        return object : Function {
            override fun f(x: Double): Double = fn(x)
        }
    }

    /**
     * A Java native utility function to create a custom function on the go
     * */
    fun createFunction(fn: java.util.function.Function<Double, Double>): Function {
        return object : Function {
            override fun f(x: Double): Double = fn.apply(x)
        }
    }

    /**
     * Create polynomial functions
     *
     * @param power the power of the polynomial
     * @return function of f(x) = x ^ power
     * */
    fun createPolynomial(power: Int): Function = object : Function {
        override fun f(x: Double): Double = x.pow(power)
    }

    /**
     * Inverse polynomial where f(0) = 0 and f(1) = 1
     *
     * @param power the power of the polynomial
     * @return function of f(x) = 1 - (1 - x) ^ power
     * */
    fun createInversePolynomial(power: Int): Function = object : Function {
        override fun f(x: Double): Double = 1 - (1 - x).pow(power)
    }
}