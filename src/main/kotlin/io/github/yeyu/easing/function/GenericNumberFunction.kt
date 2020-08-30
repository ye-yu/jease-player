package io.github.yeyu.easing.function

interface GenericNumberFunction<T: Number> {
    fun f(x: T): T
}