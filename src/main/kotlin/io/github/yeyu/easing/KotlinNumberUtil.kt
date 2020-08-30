package io.github.yeyu.easing

object KotlinNumberUtil {
    operator fun Number.plus(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() + other.toDouble()
            this is Float || other is Float -> this.toFloat() + other.toFloat()
            this is Long || other is Long -> this.toLong() + other.toLong()
            this is Int || other is Int -> this.toInt() + other.toInt()
            this is Short || other is Short -> this.toShort() + other.toShort()
            this is Byte || other is Byte -> this.toByte() + other.toByte()
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    operator fun Number.minus(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() - other.toDouble()
            this is Float || other is Float -> this.toFloat() - other.toFloat()
            this is Long || other is Long -> this.toLong() - other.toLong()
            this is Int || other is Int -> this.toInt() - other.toInt()
            this is Short || other is Short -> this.toShort() - other.toShort()
            this is Byte || other is Byte -> this.toByte() - other.toByte()
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    operator fun Number.times(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() * other.toDouble()
            this is Float || other is Float -> this.toFloat() * other.toFloat()
            this is Long || other is Long -> this.toLong() * other.toLong()
            this is Int || other is Int -> this.toInt() * other.toInt()
            this is Short || other is Short -> this.toShort() * other.toShort()
            this is Byte || other is Byte -> this.toByte() * other.toByte()
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    fun <T: Number> abs(n: T): T {
        @Suppress("UNCHECKED_CAST")
        if (n < 0) return (1 - n) as T
        return n
    }

    private operator fun Number.compareTo(other: Number): Int {
        val compare = (this - other).toDouble()
        return when {
            compare > 0 -> 1
            compare < 0 -> -1
            else -> 0
        }
    }
}
