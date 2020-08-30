package io.github.yeyu.easing.type

import kotlin.math.round

/**
 * A type depicting four color channels of a byte size
 *
 * The range for each channel must be [0, 255]. When performing calculations,
 * values out of range may happen. Therefore, it may be helpful to use the
 * [wrap] or [clip] method to overflow the values.
 *
 * The usage of this class is module specific and is not maintained for
 * other module.
 * */
class Color4C(private val c1: Int, private val c2: Int, private val c3: Int, private val c4: Int, allowOverflow: Boolean = false) : Number() {

    /**
     * The max value for each channel
     * */
    private val max = 256

    init {
        if (!allowOverflow) {
            requireRange(c1, "channel 1")
            requireRange(c2, "channel 2")
            requireRange(c3, "channel 3")
            requireRange(c4, "channel 4")
        }
    }

    /**
     * Condition for channel
     * */
    private fun requireRange(n: Int, name: String) {
        require(n in 0 until max) { "$name{$n} cannot be out of the range [0, 255]" }
    }

    /**
     * Performs addition and overflow
     * */
    fun plusWrap(other: Color4C): Color4C {
        val c1 = (this.c1 + other.c1) % max
        val c2 = (this.c2 + other.c2) % max
        val c3 = (this.c3 + other.c3) % max
        val c4 = (this.c4 + other.c4) % max
        return Color4C(c1, c2, c3, c4)
    }

    /**
     * Performs subtraction and overflow
     * */
    fun minusWrap(other: Color4C): Color4C {
        return this.plusWrap(other.negate())
    }

    /**
     * Performs overflow if value is out of bound
     * */
    fun wrap(): Color4C {
        return Color4C(c1 % max, c2 % max, c3 % max, c4 % max)
    }

    /**
     * Clips each channel to the range of [0, 255]
     * */
    fun clip(): Color4C {
        return Color4C(
            c1.coerceAtMost(max - 1).coerceAtLeast(0),
            c2.coerceAtMost(max - 1).coerceAtLeast(0),
            c3.coerceAtMost(max - 1).coerceAtLeast(0),
            c4.coerceAtMost(max - 1).coerceAtLeast(0)
        )
    }

    /**
     * Negates the value at each channel
     * */
    private fun negate(): Color4C {
        return Color4C(c1 * -1, c2 * -1, c3 * -1, c4 * -1,  true)
    }

    /**
     * Performs channel-wise addition. May produce value out of bound
     * */
    operator fun plus(other: Color4C): Color4C {
        val c1 = (this.c1 + other.c1)
        val c2 = (this.c2 + other.c2)
        val c3 = (this.c3 + other.c3)
        val c4 = (this.c4 + other.c4)
        return Color4C(c1, c2, c3, c4, true)
    }

    /**
     * Performs channel-wise subtraction. May produce value out of bound
     * */
    operator fun minus(other: Color4C): Color4C {
        val c1 = (this.c1 - other.c1)
        val c2 = (this.c2 - other.c2)
        val c3 = (this.c3 - other.c3)
        val c4 = (this.c4 - other.c4)
        return Color4C(c1, c2, c3, c4, true)
    }

    /**
     * Performs multiplication of the magnitude on each channel. May produce value out of bound
     * */
    operator fun times(other: Int): Color4C {
        val c1 = (this.c1 * other)
        val c2 = (this.c2 * other)
        val c3 = (this.c3 * other)
        val c4 = (this.c4 * other)
        return Color4C(c1, c2, c3, c4, true)
    }

    /**
     * Performs multiplication of the magnitude on each channel. May produce value out of bound
     * */
    operator fun times(other: Double): Color4C {
        val c1 = round(this.c1 * other).toInt()
        val c2 = round(this.c2 * other).toInt()
        val c3 = round(this.c3 * other).toInt()
        val c4 = round(this.c4 * other).toInt()
        return Color4C(c1, c2, c3, c4, true)
    }

    /**
     * Performs multiplication of the magnitude on each channel. May produce value out of bound
     * */
    operator fun times(other: Number): Color4C {
        return when (other) {
            is Double -> this * other
            is Int -> this * other
            is Float -> this * other.toDouble()
            else -> throw IllegalArgumentException("Convert to double or int first!")
        }
    }

    /**
     * Performs division of the magnitude on each channel. May produce value out of bound
     * */
    operator fun div(other: Int): Color4C {
        val c1 = (this.c1 / other)
        val c2 = (this.c2 / other)
        val c3 = (this.c3 / other)
        val c4 = (this.c4 / other)
        return Color4C(c1, c2, c3, c4, true)
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
        return c1 shl 24 + c2 shl 16 + c3 shl 8 + c4
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
        if (other is Color4C) {
            return other.c1 == this.c1 && other.c2 == this.c2 && other.c3 == this.c3 && other.c4 == this.c4
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = c1
        result = 31 * result + c2
        result = 31 * result + c3
        result = 31 * result + c4
        return result
    }

    override fun toString(): String {
        return "Color4C{c1:$c1, c2:$c2, c3: $c3, c4: $c4"
    }
}