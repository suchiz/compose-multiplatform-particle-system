package me.nikhilchaudhari.quarks.core

sealed interface Force {
    data class Gravity(val magnitude: Float = 0f) : Force
    data class Wind(val xDirection: Float = 0f, val yDirection: Float = 0f) : Force
}

internal fun Force.createForceVector(): Vector2D {
    return when (this) {
        is Force.Gravity -> {
            Vector2D(0f, this.magnitude)
        }

        is Force.Wind -> {
            Vector2D(this.xDirection, this.yDirection)
        }
    }
}