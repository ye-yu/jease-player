package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.number.KotlinNumberUtil.compareTo

/**
 * A simple ease function iterator based on the number of frames.
 * An ease player is best used in a looping render method.
 * In each render time, call the method `next()` to get the next
 * interpolated value.
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`lower` can be less than `upper`)
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class EasePlayer<T : Number>(
    private val lower: T,
    private val upper: T,
    private val numberOfFrames: Int,
    private val easeFactory: (T, T) -> Ease<T>
) : Iterator<T> {

    internal lateinit var easeFn: Ease<T>
    internal var current = lower
    internal var currentFrame = 1

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
    internal open var transitionTo: T = upper
        set(value) {
            if (value == field) return // does nothing
            if (value !is Comparable<*>) {
                if (value < lower) throw IllegalArgumentException("Cannot go less than $lower")
                if (value > upper) throw IllegalArgumentException("Cannot go more than $upper")
            }
            field = value
            easeFn = easeFactory(current, value)
            resetFrame()
        }

    /**
     * @return the next value of the ease interpolation
     * @throws NoSuchElementException when the iteration is exhausted
     * */
    override fun next(): T {
        if (!hasNext()) throw NoSuchElementException("There is no next")
        val selectFrame = currentFrame.toDouble() / (numberOfFrames - 1)
        current = easeFn.next(selectFrame)
        currentFrame++
        return current
    }

    /**
     * @return true if there is next value
     * */
    override fun hasNext(): Boolean = currentFrame < numberOfFrames

    /**
     * Resets the frame back to 0
     * */
    internal fun resetFrame() {
        currentFrame = 0
    }

    /**
     * @return true is the current frame has reached the end of the frame
     * */
    internal val lastOfFrame: Boolean get() = currentFrame == numberOfFrames - 2
}