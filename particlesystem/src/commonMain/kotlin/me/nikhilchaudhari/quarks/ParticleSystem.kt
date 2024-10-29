package me.nikhilchaudhari.quarks

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import me.nikhilchaudhari.quarks.core.Acceleration
import me.nikhilchaudhari.quarks.core.Force
import me.nikhilchaudhari.quarks.core.Velocity
import me.nikhilchaudhari.quarks.core.createForceVector
import me.nikhilchaudhari.quarks.emitters.ParticleExplodeEmitter
import me.nikhilchaudhari.quarks.emitters.ParticleFlowEmitter
import me.nikhilchaudhari.quarks.particle.config.EmissionType
import me.nikhilchaudhari.quarks.particle.config.LifeTime
import me.nikhilchaudhari.quarks.particle.config.ParticleColor
import me.nikhilchaudhari.quarks.particle.config.ParticleConfigData
import me.nikhilchaudhari.quarks.particle.config.ParticleSize
import me.nikhilchaudhari.quarks.utils.currentTimeMillis
import me.nikhilchaudhari.quarks.utils.currentTimeNanos

@Composable
fun CreateParticles(
    modifier: Modifier = Modifier,
    x: Float = 0f,
    y: Float = 0f,
    velocity: Velocity = Velocity(xDirection = 1f, yDirection = 1f),
    force: Force = Force.Gravity(0.0f),
    acceleration: Acceleration = Acceleration(0f, 0f),
    particleSize: ParticleSize = ParticleSize.ConstantSize(),
    particleColor: ParticleColor = ParticleColor.SingleColor(),
    lifeTime: LifeTime = LifeTime(255f, 1f),
    emissionType: EmissionType = EmissionType.ExplodeEmission(),
    durationMillis: Int = 10000,
) {
    val deltaTime = remember { mutableStateOf(0f) }
    val startTime by remember { mutableStateOf(currentTimeMillis()) }
    var previousTime by remember { mutableStateOf(currentTimeNanos()) }

    val emitter = remember {
        val particleConfigData = ParticleConfigData(
            x, y, velocity, force, acceleration, particleSize, particleColor, lifeTime, emissionType
        )
        when (emissionType) {
            is EmissionType.ExplodeEmission -> {
                ParticleExplodeEmitter(emissionType.numberOfParticles, particleConfigData)
            }

            is EmissionType.FlowEmission -> {
                ParticleFlowEmitter(
                    durationMillis,
                    emissionType,
                    particleConfigData
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        val isInfinite = emissionType is EmissionType.FlowEmission
                && emissionType.maxParticlesCount == EmissionType.FlowEmission.INDEFINITE

        while (isInfinite || currentTimeMillis() - startTime < durationMillis) {
            withFrameNanos {
                deltaTime.value = ((it - previousTime) / 1E7).toFloat()
                previousTime = it
            }
        }
    }

    Canvas(modifier = modifier) {
        emitter.render(this)
        emitter.applyForce(force.createForceVector())
        emitter.update(deltaTime.value)
    }
}
