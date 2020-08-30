package io.github.yeyu.easing

class EaseIterator<T: Number>(private val easeFn: Ease<T>, private val numberOfFrames: Int): Iterator<T> {
    private var currentFrame = -1
    private val frames = FloatArray(numberOfFrames - 1) {
        if (it == numberOfFrames - 1) 1f
        else it.toFloat() / (numberOfFrames - 1)
    }
    override fun hasNext(): Boolean = currentFrame < numberOfFrames

    override fun next(): T = easeFn.next(frames[++currentFrame])
}