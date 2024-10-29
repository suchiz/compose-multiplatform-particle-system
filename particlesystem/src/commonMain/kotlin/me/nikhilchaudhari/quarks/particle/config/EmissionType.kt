package me.nikhilchaudhari.quarks.particle.config

sealed interface EmissionType {
    data class ExplodeEmission(
        val numberOfParticles: Int = 30
    ) : EmissionType

    data class FlowEmission(
        val maxParticlesCount: Int = 50,
        val emissionRate: Float = 0.5f
    ) : EmissionType {
        companion object {
            const val INDEFINITE = -2
        }
    }
}