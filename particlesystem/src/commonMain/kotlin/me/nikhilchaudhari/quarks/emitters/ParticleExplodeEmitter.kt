package me.nikhilchaudhari.quarks.emitters

import me.nikhilchaudhari.quarks.particle.config.ParticleConfigData

internal class ParticleExplodeEmitter(
    numberOfParticles: Int,
    particleConfigData: ParticleConfigData
) : Emitter(particleConfigData) {

    init {
        generateParticles(numberOfParticles)
    }

    override fun generateParticles(numberOfParticles: Int) {
        repeat(numberOfParticles) { addParticle() }
    }

    override fun update(deltaTime: Float) {
        for (particle in particlePool) {
            particle.update(deltaTime)
        }
        particlePool.removeAll { it.isFinished }
    }
}
