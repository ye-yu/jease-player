package io.github.yeyu.easing

class IterableEase<T: Number>(private val easeFn: Ease<T>, private val numberOfFrames: Int): Iterable<T> {
    override fun iterator(): Iterator<T> = EaseIterator(easeFn, numberOfFrames)
}