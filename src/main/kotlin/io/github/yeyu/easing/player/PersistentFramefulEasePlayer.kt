package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that replays the last frame when the iteration is exhausted
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * */
open class PersistentFramefulEasePlayer<T : Number>(
    easeFn: Ease<T>,
    private val numberOfFrames: Int
) : FramefulEasePlayer<T>(easeFn, numberOfFrames) {

    /**
     * @return the next value of the ease interpolation
     * */
    override fun next(): T {
        if (currentFrame == numberOfFrames) currentFrame -= 1 // replay last frame
        return super.next()
    }
}