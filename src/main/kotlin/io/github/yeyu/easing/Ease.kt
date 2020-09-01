package io.github.yeyu.easing

import io.github.yeyu.easing.interpolator.Interpolator

/**
 * Base interface for all ease class.
 *
 * A class extending this interface can either be
 * an ease-in or an ease-out class. For better
 * clarification, extend EaseIn/EaseOut interface
 * instead.
 *
 * An ease function takes in the domain of `[0, 1]`
 * */
interface Ease<T: Number> {

    /**
     * An interpolator that has the domain of `[0, 1]` and range
     * that is defined in the implementing class
     * */
    val interpolator: Interpolator<T>

    /**
     * An interpolator that has the domain of `[0, 1]` and range
     * that is defined in the implementing class
     * */
    fun next(at: Double): T
}