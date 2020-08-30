package io.github.yeyu.easing.number

import io.github.yeyu.easing.number.KotlinNumberUtil.div
import io.github.yeyu.easing.number.KotlinNumberUtil.plus
import io.github.yeyu.easing.type.Color3C
import io.github.yeyu.easing.type.Color4C

object KotlinNumberUtil {
    operator fun Number.plus(other: Number): Number {
        return when {
            this is Double || other is Double -> this.toDouble() + other.toDouble()
            this is Float || other is Float -> this.toFloat() + other.toFloat()
            this is Long || other is Long -> this.toLong() + other.toLong()
            this is Int || other is Int -> this.toInt() + other.toInt()
            this is Short || other is Short -> this.toShort() + other.toShort()
            this is Byte || other is Byte -> this.toByte() + other.toByte()
            this is Color3C && other is Color3C -> this + other
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
            this is Color3C && other is Color3C -> this - other
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

    operator fun Number.times(other: Int): Number {
        return when (this) {
            is Color3C, is Color4C -> this * other
            is Long, is Int, is Short, is Byte -> this * other
            is Double, is Float -> this * other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    operator fun Number.div(other: Int): Number {
        return when (this) {
            is Color3C, is Color4C -> this / other
            is Long, is Int, is Short, is Byte -> this / other
            is Double, is Float -> this / other
            else -> throw RuntimeException("Unknown numeric type")
        }
    }

    fun <T: Number> abs(n: T): T {
        @Suppress("UNCHECKED_CAST")
        if (n < 0) return (1 - n) as T
        return n
    }

    operator fun Number.compareTo(other: Number): Int {
        val compare = (this - other).toDouble()
        return when {
            compare > 0 -> 1
            compare < 0 -> -1
            else -> 0
        }
    }
}
