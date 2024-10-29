package me.nikhilchaudhari.quarks.core

import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class Velocity(
    val xDirection: Float = 0f,
    val yDirection: Float = 0f,
    val angle: Double = TWO_PI,
    val randomize: Boolean = true
)

internal fun Velocity.createVelocityVector(): Vector2D {
    return if (this.randomize) {
        Vector2D(
            x = (this.xDirection * cos(angle * Random.nextFloat())).toFloat(),
            y = (this.yDirection * sin(angle * Random.nextFloat())).toFloat()
        )
    } else {
        Vector2D(
            x = (this.xDirection * cos(angle)).toFloat(),
            y = (this.yDirection * sin(angle)).toFloat()
        )
    }
}