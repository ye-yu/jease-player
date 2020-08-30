package io.github.yeyu.easing.type

import kotlin.math.round

class Color3C(private val c1: Int, private val c2: Int, private val c3: Int, allowOverflow: Boolean = false) : Number() {
    private val max = 256

    init {
        if (!allowOverflow) {
            requireRange(c1, "channel 1")
            requireRange(c2, "channel 2")
            requireRange(c3, "channel 3")
        }
    }

    private fun requireRange(n: Int, name: String) {
        require(n in 0 until max) { "$name{$n} cannot be out of the range [0, 255]" }
    }

    fun plusWrap(other: Color3C): Color3C {
        val c1 = (this.c1 + other.c1) % max
        val c2 = (this.c2 + other.c2) % max
        val c3 = (this.c3 + other.c3) % max
        return Color3C(c1, c2, c3)
    }

    fun minusWrap(other: Color3C): Color3C {
        return this.plusWrap(other.negate())
    }

    fun wrap(): Color3C {
        return Color3C(c1 % max, c2 % max, c3 % max)
    }

    private fun negate(): Color3C {
        return Color3C(c1 * -1, c2 * -1, c3 * -1, true)
    }

    operator fun plus(other: Color3C): Color3C {
        val c1 = (this.c1 + other.c1)
        val c2 = (this.c2 + other.c2)
        val c3 = (this.c3 + other.c3)
        return Color3C(c1, c2, c3, true)
    }

    operator fun minus(other: Color3C): Color3C {
        val c1 = (this.c1 - other.c1)
        val c2 = (this.c2 - other.c2)
        val c3 = (this.c3 - other.c3)
        return Color3C(c1, c2, c3, true)
    }

    operator fun times(other: Int): Color3C {
        val c1 = (this.c1 * other)
        val c2 = (this.c2 * other)
        val c3 = (this.c3 * other)
        return Color3C(c1, c2, c3, true)
    }

    operator fun times(other: Double): Color3C {
        val c1 = round(this.c1 * other).toInt()
        val c2 = round(this.c2 * other).toInt()
        val c3 = round(this.c3 * other).toInt()
        return Color3C(c1, c2, c3, true)
    }

    operator fun times(other: Number): Color3C {
        return when (other) {
            is Double -> this * other
            is Int -> this * other
            is Float -> this * other.toDouble()
            else -> throw IllegalArgumentException("Convert to double or int first!")
        }
    }

    operator fun div(other: Int): Color3C {
        val c1 = (this.c1 / other)
        val c2 = (this.c2 / other)
        val c3 = (this.c3 / other)
        return Color3C(c1, c2, c3, true)
    }

    @Deprecated("Can only convert to Int")
    override fun toByte(): Byte {
        throw IllegalAccessError("Cannot convert color to byte")
    }

    @Deprecated("Can only convert to Int")
    override fun toChar(): Char {
        throw IllegalAccessError("Cannot convert color to char")
    }

    @Deprecated("Can only convert to Int")
    override fun toDouble(): Double {
        throw IllegalAccessError("Cannot convert color to double")
    }

    @Deprecated("Can only convert to Int")
    override fun toFloat(): Float {
        throw IllegalAccessError("Cannot convert color to float")
    }

    override fun toInt(): Int {
        return c1 shl 16 + c2 shl 8 + c3
    }

    @Deprecated("Can only convert to Int")
    override fun toLong(): Long {
        throw IllegalAccessError("Cannot convert color to long")
    }

    @Deprecated("Can only convert to Int")
    override fun toShort(): Short {
        throw IllegalAccessError("Cannot convert color to short")
    }

    override fun equals(other: Any?): Boolean {
        if (other is Color3C) {
            return other.c1 == this.c1 && other.c2 == this.c2 && other.c3 == this.c3
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = c1
        result = 31 * result + c2
        result = 31 * result + c3
        return result
    }
}