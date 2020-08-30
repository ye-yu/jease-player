package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease
import io.github.yeyu.easing.number.KotlinNumberUtil.compareTo
import io.github.yeyu.easing.type.Color3C
import io.github.yeyu.easing.type.Color4C

open class EasePlayer<T : Number>(
        private val lower: T,
        private val upper: T,
        private val numberOfFrames: Int,
        private val easeFactory: (T, T) -> Ease<T>): Iterator<T> {

    internal lateinit var easeFn: Ease<T>
    internal var current = lower
    internal var currentFrame = 1

    /**
     * @throws IllegalAccessError transitionTo can only be set
     * */
    open var transitionTo: T = lower
        set(value) {
            if (value is Color3C || value is Color4C) field = value
            if (value < lower) throw IllegalArgumentException("Cannot go less than $lower")
            if (value > upper) throw IllegalArgumentException("Cannot go more than $upper")
            field = value
            easeFn = easeFactory(current, value)
            resetFrame()
        }
        get() { throw IllegalAccessError("Accessing private property") }

    override fun next(): T {
        if (currentFrame == numberOfFrames - 1) throw IndexOutOfBoundsException("There is no next")
        val selectFrame = if (currentFrame == numberOfFrames - 2) 1.0f else (currentFrame + 1).toFloat() / (numberOfFrames - 1)
        current = easeFn.next(selectFrame)
        currentFrame++
        return current
    }

    override fun hasNext(): Boolean = currentFrame < numberOfFrames - 1

    internal fun resetFrame() {
        currentFrame = 0
    }
}