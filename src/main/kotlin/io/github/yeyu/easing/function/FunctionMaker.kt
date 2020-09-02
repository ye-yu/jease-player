package io.github.yeyu.easing.function

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
}