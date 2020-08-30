package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

class RollingEasePlayer<T : Number>(
        private val lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : PersistentEasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    var rollNext = false
    override fun next(): T {
        if (rollNext) {
            resetFrame()
            rollNext = false
            return lower
        }
        if (currentFrame == numberOfFrames - 1) rollNext = true
        return super.next()
    }
}