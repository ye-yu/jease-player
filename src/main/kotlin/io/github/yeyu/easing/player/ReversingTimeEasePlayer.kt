@file:Suppress("DEPRECATION")

package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.interpolator.LongToGenericCoordinateInterpolator2D

/**
 * An ease player that reverse after reaching the end of the frame and again
 * after reaching the start of the frame.
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`upper` can be less than `lower`)
 * @param animationDuration the approximate duration of the whole ease
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class ReversingTimeEasePlayer<T : Number>(
    lower: T,
    upper: T,
    private val animationDuration: Long,
    easeFactory: (T, T) -> Ease<T>
) : PersistentTimeEasePlayer<T>(lower, upper, animationDuration, easeFactory) {
    /**
     * A stateful property to indicate when to start reversing
     * the next frame
     * */
    private var reverse = false

    @Suppress("DEPRECATION")
    internal val reverseTimeInterpolator = LongToGenericCoordinateInterpolator2D(0, 1.0, animationDuration, 0.0)

    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        var timeDelta = timeInstance - currentEpoch
        if (timeDelta > animationDuration) {
            reverse = !reverse
            currentEpoch += animationDuration * Math.floorDiv(timeDelta, animationDuration)
            timeDelta = timeInstance - currentEpoch
        }
        val selectFrame = if (reverse) reverseTimeInterpolator.next(timeDelta).coerceAtMost(1.0)
            else timeInterpolator.next(timeDelta).coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }
}