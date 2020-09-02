package io.github.yeyu.easing.number

import io.github.yeyu.easing.type.Color3C
import io.github.yeyu.easing.type.Color4C

/**
 * A Kotlin number utility method to perform calculation
 * for sub-classes of Number.
 *
 * The number is casted with the priority of:
 *   - Double
 *   - Float
 *   - Long
 *   - Int
 *   - Short
 *   - Byte
 *   - Color (if applicable)
 *
 * in that particular order.
 * */
object KotlinNumberUtil {

    /**
     * Performs `Number + Number`
     * */
    operator fun Number.plus(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() + other.toDouble()
            this is Float || other is Float -> this.toFloat() + other.toFloat()
            this is Long || other is Long -> this.toLong() + other.toLong()
            this is Int || other is Int -> this.toInt() + other.toInt()
            this is Short || other is Short -> this.toShort() + other.toShort()
            this is Byte || other is Byte -> this.toByte() + other.toByte()
            this is Color3C && other is Color3C -> this + other
            this is Color4C && other is Color4C -> this + other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs `Number - Number`
     * */
    operator fun Number.minus(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() - other.toDouble()
            this is Float || other is Float -> this.toFloat() - other.toFloat()
            this is Long || other is Long -> this.toLong() - other.toLong()
            this is Int || other is Int -> this.toInt() - other.toInt()
            this is Short || other is Short -> this.toShort() - other.toShort()
            this is Byte || other is Byte -> this.toByte() - other.toByte()
            this is Color3C && other is Color3C -> this - other
            this is Color4C && other is Color4C -> this - other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs `Number * Number`
     * */
    operator fun Number.times(other: Number): Number {
        return when {
            this is Color3C -> this * other
            this is Color4C -> this * other
            this is Double || other is Double -> this.toDouble() * other.toDouble()
            this is Float || other is Float -> this.toFloat() * other.toFloat()
            this is Long || other is Long -> this.toLong() * other.toLong()
            this is Int || other is Int -> this.toInt() * other.toInt()
            this is Short || other is Short -> this.toShort() * other.toShort()
            this is Byte || other is Byte -> this.toByte() * other.toByte()
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs `Number / Number`
     * */
    operator fun Number.div(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() / other.toDouble()
            this is Float || other is Float -> this.toFloat() / other.toFloat()
            this is Long || other is Long -> this.toLong() / other.toLong()
            this is Int || other is Int -> this.toInt() / other.toInt()
            this is Short || other is Short -> this.toShort() / other.toShort()
            this is Byte || other is Byte -> this.toByte() / other.toByte()
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs `Number * Int`
     * */
    operator fun Number.times(other: Int): Number {
        return when (this) {
            is Color3C -> this * other
            is Color4C -> this * other
            is Double -> this * other
            is Float -> this * other
            is Long -> this * other
            is Int -> this * other
            is Short -> this * other
            is Byte -> this * other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs `Number / Int`
     * */
    operator fun Number.div(other: Int): Number {
        return when (this) {
            is Color3C -> this / other
            is Color4C -> this / other
            is Double -> this / other
            is Float -> this / other
            is Long -> this / other
            is Int -> this / other
            is Short -> this / other
            is Byte -> this / other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    /**
     * Performs comparison between two number
     * */
    operator fun Number.compareTo(other: Number): Int {
        if (other is Comparable<*> && this is Comparable<*>) {
            return when {
                this is Double || other is Double -> this.toDouble().compareTo(other.toDouble())
                this is Float || other is Float -> this.toFloat().compareTo(other.toFloat())
                this is Long || other is Long -> this.toLong().compareTo(other.toLong())
                this is Int || other is Int -> this.toInt().compareTo(other.toInt())
                this is Short || other is Short -> this.toShort().compareTo(other.toShort())
                this is Byte || other is Byte -> this.toByte().compareTo(other.toByte())
                else -> throw IllegalArgumentException("Unknown numeric type")
            }
        }
        throw IllegalArgumentException("Class does not implement Comparable")
    }
}
