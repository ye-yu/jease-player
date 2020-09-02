package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function
import io.github.yeyu.easing.function.LinearFunction

class GenericInterpolator1D<T : Number>(
        override val from: T,
        override val to: T,
        override val function: Function = LinearFunction) : Interpolator<T>