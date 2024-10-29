package me.nikhilchaudhari.quarks.utils

import platform.Foundation.NSDate
import platform.Foundation.NSDecimalNumber
import platform.Foundation.NSDecimalNumberHandler
import platform.Foundation.NSRoundingMode
import platform.Foundation.timeIntervalSince1970
import kotlin.time.TimeSource

actual fun Float.roundTo(n: Int): Float {
    val decimal = NSDecimalNumber( this.toDouble())
    val handler = NSDecimalNumberHandler(
        roundingMode = NSRoundingMode.NSRoundUp,
        scale = n.toShort(),
        raiseOnExactness = false,
        raiseOnOverflow = false,
        raiseOnUnderflow = false,
        raiseOnDivideByZero = false
    )
    return decimal.decimalNumberByRoundingAccordingToBehavior(handler).floatValue
}

actual fun currentTimeNanos(): Long {
    return TimeSource.Monotonic.markNow().elapsedNow().inWholeNanoseconds
}

actual fun currentTimeMillis(): Long {
    return (NSDate().timeIntervalSince1970 * 1000).toLong()
}