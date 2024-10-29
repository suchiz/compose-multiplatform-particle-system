package me.nikhilchaudhari.quarks.utils

import java.math.RoundingMode

actual fun Float.roundTo(n: Int): Float {
    return this.toBigDecimal().setScale(n, RoundingMode.UP).toFloat()
}

actual fun currentTimeNanos(): Long {
    return System.nanoTime()
}

actual fun currentTimeMillis(): Long {
    return System.currentTimeMillis()
}