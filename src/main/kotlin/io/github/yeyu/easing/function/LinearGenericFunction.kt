package io.github.yeyu.easing.function

object LinearGenericFunction {
    operator fun <T: Number> invoke(): GenericNumberFunction<T> {
        return object: GenericNumberFunction<T> {
            override fun f(x: T): T = x
        }
    }
}