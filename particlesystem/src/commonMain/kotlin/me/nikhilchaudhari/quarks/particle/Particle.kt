package me.nikhilchaudhari.quarks.particle

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import me.nikhilchaudhari.quarks.core.Vector2D
import me.nikhilchaudhari.quarks.core.add
import me.nikhilchaudhari.quarks.core.scalarMultiply
import me.nikhilchaudhari.quarks.utils.roundTo

internal class Particle(
    val initialX: Float = 0f,
    val initialY: Float = 0f,
    val color: Color = Color.Yellow,
    val size: Float = 25f,
    val velocity: Vector2D = Vector2D(0f, 0f),
    val acceleration: Vector2D = Vector2D(0f, 0f),
    var lifetime: Float = 255f,
    val agingFactor: Float = 20f
) : Vector2D(initialX, initialY) {

    private val originalLife = lifetime
    private var alpha = 1f

    val isFinished: Boolean get() = this.lifetime < 0

    fun applyForce(force: Vector2D) {
        this.acceleration.add(force)
    }

    fun update(dt: Float) {
        lifetime -= agingFactor

        if (lifetime >= 0) {
            this.alpha = (lifetime / originalLife).roundTo(3)
        }

        // Add acceleration to velocity vector
        this.velocity.add(acceleration)

        // add velocity vector to positions
        this.add(velocity, scalar = dt)

        //set acceleration back to 0
        this.acceleration.scalarMultiply(0f)
    }

    fun show(drawScope: DrawScope) {
        drawScope.drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 360f,
            alpha = alpha,
            topLeft = Offset(x, y),
            size = Size(size, size),
            useCenter = true
        )
    }
}
