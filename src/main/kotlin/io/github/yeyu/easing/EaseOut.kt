package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * An ease-in interface. Ease-inout means both the
 * beginning and the end of the transition is not abrupt.
 *
 * An ease function takes in the domain of `[0, 1]`
 * */
interface EaseOut<T: Number> : Ease<T> {
    /**
     * An ease-out function
     * */
    val easeOutFunction: Function

    /**
     * An interpolator that has the domain of `[0, 1]` and range
     * that is defined in the implementing class
     * */
    val easeOutInterpolator1D: Interpolator<T>
        get() = interpolator
}