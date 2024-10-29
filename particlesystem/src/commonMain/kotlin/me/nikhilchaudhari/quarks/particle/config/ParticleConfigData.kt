package me.nikhilchaudhari.quarks.particle.config

import me.nikhilchaudhari.quarks.core.Acceleration
import me.nikhilchaudhari.quarks.core.Force
import me.nikhilchaudhari.quarks.core.Velocity

internal data class ParticleConfigData(
    val x: Float = 0f,
    val y: Float = 0f,
    val velocity: Velocity,
    val force: Force,
    val acceleration: Acceleration,
    val particleSize: ParticleSize,
    val particleColor: ParticleColor,
    val lifeTime: LifeTime,
    val emissionType: EmissionType,
)

