package me.nikhilchaudhari.quarks.core

import kotlin.random.Random

data class Acceleration(
    val xComponent: Float = 0f,
    val yComponent: Float = 0f,
    val uniform: Boolean = false
)

internal fun Acceleration.createAccelerationVector(): Vector2D {
    return when (uniform) {
        true -> Vector2D(this.xComponent, this.yComponent)
        false -> Vector2D(xComponent * Random.nextFloat(), yComponent * Random.nextFloat())
    }
}