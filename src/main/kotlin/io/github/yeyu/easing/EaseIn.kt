package io.github.yeyu.easing

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.interpolator.Interpolator

/**
 * An ease-in interface. Ease-in means the
 * beginning of the transition is not abrupt.
 *
 * An ease function takes in the domain of `[0, 1]`
 * */
interface EaseIn<T: Number>: Ease<T> {
    /**
     * An ease-in function
     * */
    val easeInFunction: Function

    /**
     * An interpolator that has the domain of `[0, 1]` and range
     * that is defined in the implementing class
     * */
    val easeInInterpolator1D: Interpolator<T>
        get() = interpolator
}