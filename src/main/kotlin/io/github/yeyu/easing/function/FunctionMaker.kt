package io.github.yeyu.easing.function

object FunctionMaker {
    fun createFunction(fn: (Double) -> Double): Function {
        return object: Function {
            override fun f(x: Double): Double = fn(x)
        }
    }
}