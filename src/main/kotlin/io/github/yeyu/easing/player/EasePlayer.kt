package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * A base interface for all ease players
 * */
interface EasePlayer<T : Number> {
    /**
     * Sets the ease to transit to an intermediate value.
     * Every reset of `transitionTo` will reset the iterator
     * and regenerate the following frames.
     *
     * Does nothing when the new transition value is the same with
     * the previous value.
     *
     * Performs extra checking of bound when the parameter is a type of
     * comparable so that the new value is not outside of `lower` and `upper` bound.
     * */
    var transitionTo: T

    /**
     * The current ease function
     * */
    val easeFn: Ease<T>

    /**
     * Resets the frame back to the beginning
     * */
    fun reset()
    fun next(): T
}