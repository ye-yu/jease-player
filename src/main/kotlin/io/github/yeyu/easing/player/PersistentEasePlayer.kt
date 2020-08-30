package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that replays the last frame when the iteration is exhausted
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`lower` can be less than `upper`)
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class PersistentEasePlayer<T : Number>(
        lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    /**
     * @return the next value of the ease interpolation
     * */
    override fun next(): T {
        if (currentFrame == numberOfFrames) currentFrame -= 1 // replay last frame
        return super.next()
    }
}