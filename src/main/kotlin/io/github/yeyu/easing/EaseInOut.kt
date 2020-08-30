package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.Interpolator

interface EaseInOut<T: Number>: EaseIn<T>, EaseOut<T> {
    override val interpolator: Interpolator<T>
        get() = throw IllegalAccessError("EaseInOut class has two interpolators.")

}