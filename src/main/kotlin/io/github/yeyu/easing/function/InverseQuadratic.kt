package io.github.yeyu.easing.function

/**
 * Domain: [0, 1]
 * Range: [0, 1]
 * */
object InverseQuadratic: Function {
    /**
     * Domain: [0, 1]
     * Range: [0, 1]
     * @throws IllegalArgumentException when x is out of the domain
     * */
    override fun f(x: Double): Double {
        require(x >= 0) { "x cannot be less than 0" }
        require(x <= 1) { "x cannot be more than 1" }
        return 1 - (1 - x) * (1 - x)
    }
}