@file:Suppress("DEPRECATION")

package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.interpolator.GenericInterpolator
import io.github.yeyu.easing.interpolator.LongToDoubleInterpolator

/**
 * An ease player that replays the last frame when reaching the end of the animation time.
 *
 * @param easeFn the ease instance with pre-defined `from` and `to` value
 * @param animationDuration the approximate duration of the whole ease
 * */
open class PersistentTimeEasePlayer<T : Number>(
    final override var easeFn: Ease<T>,
    private val animationDuration: Long
) : EasePlayer<T> {

    /**
     * The current interpolated value
     * */
    internal var current = easeFn.from

    internal var currentEpoch: Long = System.currentTimeMillis()

    override var transitionTo: T = easeFn.to
        set(value) {
            if (current == value) return // does nothing
            field = value
            easeFn.from = current
            easeFn.to = field
            currentEpoch = System.currentTimeMillis()
        }

    internal val timeInterpolator: GenericInterpolator<Long, Double> = LongToDoubleInterpolator

    /**
     * @return the next value of the ease interpolation
     * */
    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        val timeDelta = timeInstance - currentEpoch
        val selectFrame =
            timeInterpolator.interpolateCoordinate(0, animationDuration, 0.0, 1.0, LinearFunction, timeDelta)
                .coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }

    override fun reset() {
        currentEpoch = System.currentTimeMillis()
    }
}