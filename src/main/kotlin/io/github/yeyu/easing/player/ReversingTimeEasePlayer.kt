package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.interpolator.LongToDoubleInterpolator

/**
 * An ease player that reverse after reaching the end of the frame and again
 * after reaching the start of the frame.
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param animationDuration the approximate duration of the whole ease
 * */
open class ReversingTimeEasePlayer<T : Number>(
    easeFn: Ease<T>,
    private val animationDuration: Long
) : PersistentTimeEasePlayer<T>(easeFn, animationDuration) {
    /**
     * A stateful property to indicate when to start reversing
     * the next frame
     * */
    private var reverse = false

    @Suppress("DEPRECATION")
    internal val reverseTimeInterpolator = LongToDoubleInterpolator

    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        var timeDelta = timeInstance - currentEpoch
        if (timeDelta > animationDuration) {
            reverse = !reverse
            currentEpoch += animationDuration * Math.floorDiv(timeDelta, animationDuration)
            timeDelta = timeInstance - currentEpoch
        }
        val selectFrame = if (reverse) reverseTimeInterpolator.interpolateCoordinate(
            0,
            animationDuration,
            0.0,
            1.0,
            LinearFunction,
            timeDelta
        ).coerceAtMost(1.0)
        else timeInterpolator.interpolateCoordinate(0, animationDuration, 0.0, 1.0, LinearFunction, timeDelta)
            .coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }
}