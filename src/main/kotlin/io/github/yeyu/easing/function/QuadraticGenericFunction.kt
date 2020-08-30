package io.github.yeyu.easing.function

import io.github.yeyu.easing.number.KotlinNumberUtil.times

@Suppress("UNCHECKED_CAST")
object QuadraticGenericFunction {
    operator fun <T: Number> invoke(): GenericNumberFunction<T> {
        return object: GenericNumberFunction<T> {
            override fun f(x: T): T = (x * x) as T
        }
    }
}