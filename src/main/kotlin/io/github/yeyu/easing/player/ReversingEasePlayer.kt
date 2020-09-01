package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that reverse after reaching the end of the frame and again
 * after reaching the start of the frame.
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`upper` can be less than `lower`)
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class ReversingEasePlayer<T : Number>(
        lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    /**
     * A stateful property to indicate when to start reversing
     * the next frame
     * */
    private var reverse = false

    override var transitionTo: T
        get() = super.transitionTo
        set(value) {
            super.transitionTo = value
            reverse = false // reset transition
        }

    /**
     * @return the next value of the ease interpolation
     * */
    override fun next(): T {
        val selectFrame = currentFrame.toDouble() / (numberOfFrames - 1)
        current = easeFn.next(selectFrame)
        if (currentFrame == 0) reverse = false
        if (currentFrame == numberOfFrames - 1) reverse = true
        if (reverse) currentFrame-- else currentFrame++
        return current
    }
}