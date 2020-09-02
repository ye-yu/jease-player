package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that reverse after reaching the end of the frame and again
 * after reaching the start of the frame.
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * */
open class ReversingFramefulEasePlayer<T : Number>(
    easeFn: Ease<T>,
    private val numberOfFrames: Int
) : FramefulEasePlayer<T>(easeFn, numberOfFrames) {

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