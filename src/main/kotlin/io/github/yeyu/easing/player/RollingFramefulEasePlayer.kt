package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that restarts the frame when the last frame has been reached
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`upper` can be less than `lower`)
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
class RollingFramefulEasePlayer<T : Number>(
        private val lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : PersistentFramefulEasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    override fun next(): T {
        return super.next().also {
            if (currentFrame == numberOfFrames) reset()
        }
    }
}