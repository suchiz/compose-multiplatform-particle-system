package me.nikhilchaudhari.quarks.particle.config

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

sealed interface ParticleColor {
    data class SingleColor(val color: Color = Color.Yellow) : ParticleColor
    data class RandomColors(val colors: List<Color>) : ParticleColor
}

internal fun ParticleColor.getExactColor(): Color {
    return when (this) {
        is ParticleColor.SingleColor -> this.color
        is ParticleColor.RandomColors -> this.colors[Random.nextInt(0, this.colors.size)]
    }
}