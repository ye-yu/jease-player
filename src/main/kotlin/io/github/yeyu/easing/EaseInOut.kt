package io.github.yeyu.easing

/**
 * An ease-in interface. Ease-out means the
 * ending of the transition is not abrupt.
 *
 * An ease function takes in the domain of `[0, 1]`
 * */
interface EaseInOut<T : Number> : EaseIn<T>, EaseOut<T>