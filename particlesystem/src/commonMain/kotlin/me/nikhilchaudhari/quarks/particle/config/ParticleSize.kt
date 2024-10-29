package me.nikhilchaudhari.quarks.particle.config

import kotlin.random.Random

sealed interface ParticleSize {
    data class ConstantSize(val size: Float = 25f) : ParticleSize
    data class RandomSizes(val range: IntRange = 25..50) : ParticleSize
}

internal fun ParticleSize.getExactSize(): Float {
    return when (this) {
        is ParticleSize.ConstantSize -> this.size
        is ParticleSize.RandomSizes -> (Random.nextInt(this.range.first, this.range.last)).toFloat()
    }
}