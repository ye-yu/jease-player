@file:Suppress("DEPRECATION")

package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.interpolator.LongToGenericCoordinateInterpolator2D
import io.github.yeyu.easing.number.KotlinNumberUtil.compareTo

/**
 * An ease player that replays the last frame when the iteration is exhausted
 *
 * @param lower the lower bound of the ease (`lower` can be more than `upper`)
 * @param upper the upper bound of the ease (`upper` can be less than `lower`)
 * @param animationDuration the approximate duration of the whole ease
 * @param easeFactory the ease factory to create new ease method when `transitionTo` property is set
 * */
open class PersistentTimeEasePlayer<T : Number>(
    private val lower: T,
    private val upper: T,
    private val animationDuration: Long,
    private val easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T> {

    /**
     * The current ease function
     * */
    override var easeFn: Ease<T> = easeFactory(lower, upper)

    /**
     * The current interpolated value
     * */
    internal var current = lower

    internal var currentEpoch: Long = System.currentTimeMillis()

    @Suppress("DEPRECATION")
    internal val timeInterpolator = LongToGenericCoordinateInterpolator2D(0, 0.0, animationDuration, 1.0)

    override var transitionTo: T = upper
        set(value) {
            if (current == field) return // does nothing
            if (value is Comparable<*>) {
                if (value < lower) throw IllegalArgumentException("Cannot go less than $lower")
                if (value > upper) throw IllegalArgumentException("Cannot go more than $upper")
            }
            field = value
            easeFn = easeFactory(current, value)
            currentEpoch = System.currentTimeMillis()
        }

    /**
     * @return the next value of the ease interpolation
     * */
    override fun next(): T {
        val timeInstance = System.currentTimeMillis()
        val selectFrame = timeInterpolator.next(timeInstance - currentEpoch).coerceAtMost(1.0)
        current = easeFn.next(selectFrame)
        return current
    }

    override fun reset() {
        currentEpoch = System.currentTimeMillis()
    }
}