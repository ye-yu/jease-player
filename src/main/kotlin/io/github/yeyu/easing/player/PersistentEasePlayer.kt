package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

class PersistentEasePlayer<T : Number>(
        lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    override fun next(): T {
        if (currentFrame >= numberOfFrames) currentFrame = numberOfFrames - 1
        return super.next()
    }
}