package io.github.yeyu.easing.type

data class Color4C(private val c1: Int, private val c2: Int, private val c3: Int, private val c4: Int): Number() {
    init {
        requireRange(c1, "channel 1")
        requireRange(c2, "channel 2")
        requireRange(c3, "channel 3")
        requireRange(c4, "channel 4")
    }
    
    private val max = 256

    private fun requireRange(n: Int, name: String) {
        require(n in 0 until max) { "$name cannot be out of the range [0, 255]" }
    }

    operator fun plus(other: Color4C): Color4C {
        val c1 = (this.c1 + other.c1) % max
        val c2 = (this.c2 + other.c2) % max
        val c3 = (this.c3 + other.c3) % max
        val c4 = (this.c4 + other.c4) % max
        return Color4C(c1, c2, c3, c4)
    }

    operator fun minus(other: Color4C): Color4C {
        val c1 = (this.c1 - other.c1) % max
        val c2 = (this.c2 - other.c2) % max
        val c3 = (this.c3 - other.c3) % max
        val c4 = (this.c4 - other.c4) % max
        return Color4C(c1, c2, c3, c4)
    }

    operator fun times(other: Int): Color4C {
        val c1 = (this.c1 * other) % max
        val c2 = (this.c2 * other) % max
        val c3 = (this.c3 * other) % max
        val c4 = (this.c4 * other) % max
        return Color4C(c1, c2, c3, c4)
    }

    operator fun div(other: Int): Color4C {
        val c1 = (this.c1 / other) % max
        val c2 = (this.c2 / other) % max
        val c3 = (this.c3 / other) % max
        val c4 = (this.c4 / other) % max
        return Color4C(c1, c2, c3, c4)
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
}