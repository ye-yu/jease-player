package io.github.yeyu.easing.player

import io.github.yeyu.easing.Ease

class PersistentEasePlayer<T : Number>(
        lower: T,
        upper: T,
        private val numberOfFrames: Int,
        easeFactory: (T, T) -> Ease<T>
) : EasePlayer<T>(lower, upper, numberOfFrames, easeFactory) {

    override fun next(): T {
        if (currentFrame == numberOfFrames - 1) currentFrame -= 1 // replay last frame
        return super.next()
    }
}