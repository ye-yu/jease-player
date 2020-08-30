package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.Interpolator

/**
 * An ease-in interface. Ease-out means the
 * ending of the transition is not abrupt.
 *
 * An ease function takes in the domain of [0, 1]
 * */
interface EaseInOut<T: Number>: EaseIn<T>, EaseOut<T> {
    override val interpolator: Interpolator<T>
        get() = throw IllegalAccessError("EaseInOut class has two interpolators.")

}