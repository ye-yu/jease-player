package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * A simple ease function iterator based on the number of frames.
 * An ease player is best used in a looping render method.
 * In each render time, call the method `next()` to get the next
 * interpolated value.
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * */
open class FramefulEasePlayer<T : Number>(
    final override val easeFn: Ease<T>,
    private val numberOfFrames: Int,
) : EasePlayer<T>, Iterator<T> {

    /**
     * The current interpolated value
     * */
    internal var current = easeFn.from

    /**
     * The current frame
     * */
    internal var currentFrame = 0

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
    override var transitionTo: T = easeFn.to
        set(value) {
            if (current == value) return
            field = value
            easeFn.from = current
            easeFn.to = field
            reset()
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

    override fun reset() {
        currentFrame = 0
    }
}