package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

open class ReversingEasePlayer<T : Number>(
        lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    var reverse = false

    override var transitionTo: T
        get() = super.transitionTo
        set(value) {
            super.transitionTo = value
            reverse = false // reset transition
        }

    override fun next(): T {
        val selectFrame = if (currentFrame == numberOfFrames - 2) 1.0f else (currentFrame + 1).toFloat() / (numberOfFrames - 1)
        current = easeFn.next(selectFrame)
        if (reverse) currentFrame-- else currentFrame++
        if (currentFrame == numberOfFrames - 1) reverse = true
        if (currentFrame == 0) reverse = false
        return current
    }
}