package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.function.LinearFunction


/**
 * An ease player that restarts the frame when the last frame has been reached
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param animationDuration the approximate duration of the whole ease
 * */
open class RollingTimeEasePlayer<T : Number>(
    easeFn: Ease<T>,
    private val animationDuration: Long
) : PersistentTimeEasePlayer<T>(easeFn, animationDuration) {
    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        var timeDelta = timeInstance - currentEpoch
        if (timeDelta >= animationDuration) {
            currentEpoch += animationDuration * Math.floorDiv(timeDelta, animationDuration)
            timeDelta = timeInstance - currentEpoch
        }
        val selectFrame =
            timeInterpolator.interpolateCoordinate(0, animationDuration, 0.0, 1.0, LinearFunction, timeDelta)
                .coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }
}