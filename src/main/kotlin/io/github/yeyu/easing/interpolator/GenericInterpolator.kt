package io.github.yeyu.easing.interpolator

import io.github.yeyu.easing.function.Function

/**
 * A generic interface interpolator
 *
 * @param D domain
 * @param R range
 * */
interface GenericInterpolator<D : Number, R : Number> {

    /**
     * Interpolates by using domain of `[0, 1]`
     * */
    fun interpolateNext(lowerBound: R, upperBound: R, function: Function, at: D): R

    fun interpolateCoordinate(x1: D, x2: D, y1: R, y2: R, function: Function, at: D): R
}