package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

/**
 * An ease player that restarts the frame when the last frame has been reached
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param numberOfFrames the expected number of calls. The lower the frames, the faster the ease player.
 * */
class RollingFramefulEasePlayer<T : Number>(
    easeFn: Ease<T>,
    private val numberOfFrames: Int
) : PersistentFramefulEasePlayer<T>(easeFn, numberOfFrames) {

    override fun next(): T {
        return super.next().also {
            if (currentFrame == numberOfFrames) reset()
        }
    }
}