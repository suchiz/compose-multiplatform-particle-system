package me.nikhilchaudhari.quarks.emitters

import androidx.compose.ui.graphics.drawscope.DrawScope
import me.nikhilchaudhari.quarks.core.Vector2D
import me.nikhilchaudhari.quarks.core.createAccelerationVector
import me.nikhilchaudhari.quarks.core.createVelocityVector
import me.nikhilchaudhari.quarks.particle.Particle
import me.nikhilchaudhari.quarks.particle.config.ParticleConfigData
import me.nikhilchaudhari.quarks.particle.config.getExactColor
import me.nikhilchaudhari.quarks.particle.config.getExactSize

internal abstract class Emitter(
    private val particleConfigData: ParticleConfigData
) {
    protected val particlePool = mutableListOf<Particle>()

    abstract fun generateParticles(numberOfParticles: Int)
    abstract fun update(deltaTime: Float)

    open fun addParticle() {
        val particle = createFreshParticle()
        particlePool.add(particle)
    }

    open fun applyForce(force: Vector2D) {
        for (particle in particlePool) {
            particle.applyForce(force)
        }
    }

    open fun render(drawScope: DrawScope) {
        for (particle in particlePool) {
            particle.show(drawScope)
        }
    }

    private fun createFreshParticle(): Particle {
        return Particle(
            initialX = particleConfigData.x,
            initialY = particleConfigData.y,
            color = particleConfigData.particleColor.getExactColor(),
            size = particleConfigData.particleSize.getExactSize(),
            velocity = particleConfigData.velocity.createVelocityVector(),
            acceleration = particleConfigData.acceleration.createAccelerationVector(),
            lifetime = particleConfigData.lifeTime.maxLife,
            agingFactor = particleConfigData.lifeTime.agingFactor,
        )
    }
}
