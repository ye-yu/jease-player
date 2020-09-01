package io.github.yeyu.easing.function

/**
 * Inverse quadratic of `f(x) = 1 - (1 - x) ^ 2`
 *
 * Domain: `[0, 1]`
 *
 * Range: `[0, 1]`
 * */
object InverseQuadratic: Function {

    private const val tolerance = 0.00000015
    /**
     * Domain: `[0, 1]`
     *
     * Range: `[0, 1]`
     * @throws IllegalArgumentException when x is out of the domain
     * */
    override fun f(x: Double): Double {
        require(x >= 0.0 - tolerance) { "x{$x} cannot be less than 0" }
        require(x <= 1.0 + tolerance) { "x{$x} cannot be more than 1" }
        return 1 - (1 - x) * (1 - x)
    }
}