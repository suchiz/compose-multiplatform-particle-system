package me.nikhilchaudhari.quarks.emitters

import me.nikhilchaudhari.quarks.particle.config.EmissionType
import me.nikhilchaudhari.quarks.particle.config.ParticleConfigData

internal class ParticleFlowEmitter(
    private val durationMillis: Int,
    private val emissionConfig: EmissionType.FlowEmission,
    particleConfigData: ParticleConfigData
) : Emitter(particleConfigData) {

    private var particleCount = 0
    private var elapsed = 0f
    private var elapsedTimeParticleCreation = 0f

    override fun generateParticles(numberOfParticles: Int) {
        if (emissionConfig.maxParticlesCount in 1..(particleCount)) {
            return
        }

        particleCount++
        repeat(numberOfParticles) { addParticle() }
    }

    override fun update(deltaTime: Float) {
        elapsedTimeParticleCreation += deltaTime

        if (elapsedTimeParticleCreation >= 1 && !isTimeElapsed()) {
            val amount = (emissionConfig.emissionRate * elapsedTimeParticleCreation).toInt()
            generateParticles(amount)
            elapsedTimeParticleCreation %= 1
        }

        elapsed += deltaTime

        for (i in particlePool.size - 1 downTo 0) {
            val particle = particlePool[i]
            particle.update(deltaTime)
        }

        particlePool.removeAll { it.isFinished }
    }

    private inline fun isTimeElapsed(): Boolean {
        return when (durationMillis) {
            0 -> false
            EmissionType.FlowEmission.INDEFINITE -> false
            else -> elapsed >= durationMillis
        }
    }
}
