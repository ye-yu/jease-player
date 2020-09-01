package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease


/**
 * An ease player that restarts the frame when the last frame has been reached
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`upper` can be less than `lower`)
 * @param animationDuration the approximate duration of the whole ease
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class RollingTimeEasePlayer<T : Number>(
    lower: T,
    upper: T,
    private val animationDuration: Long,
    easeFactory: (T, T) -> Ease<T>
) : PersistentTimeEasePlayer<T>(lower, upper, animationDuration, easeFactory) {
    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        var timeDelta = timeInstance - currentEpoch
        if (timeDelta >= animationDuration) {
            currentEpoch += animationDuration * Math.floorDiv(timeDelta, animationDuration)
            timeDelta = timeInstance - currentEpoch
        }
        val selectFrame = timeInterpolator.next(timeDelta).coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }
}