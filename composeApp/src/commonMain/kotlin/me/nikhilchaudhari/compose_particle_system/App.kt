package me.nikhilchaudhari.compose_particle_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.nikhilchaudhari.quarks.CreateParticles
import me.nikhilchaudhari.quarks.core.Acceleration
import me.nikhilchaudhari.quarks.core.Force
import me.nikhilchaudhari.quarks.core.PI
import me.nikhilchaudhari.quarks.core.Velocity
import me.nikhilchaudhari.quarks.particle.config.EmissionType
import me.nikhilchaudhari.quarks.particle.config.LifeTime
import me.nikhilchaudhari.quarks.particle.config.ParticleColor
import me.nikhilchaudhari.quarks.particle.config.ParticleSize
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Fountain()
//            Meteor()
//            Confetti()
//            SnowFall()
//            Explosion()
        }
    }
}

@Composable
fun Fountain() {
    CreateParticles(
        modifier = Modifier.fillMaxSize(),
        x = 500f, y = 2000f,
        velocity = Velocity(xDirection = 1f, yDirection = -15f, angle = PI, randomize = true),
        force = Force.Gravity(0.2f),
        acceleration = Acceleration(0f, -4f),
        particleSize = ParticleSize.RandomSizes(10..20),
        particleColor = ParticleColor.RandomColors(listOf(Color.Blue, Color.Cyan)),
        lifeTime = LifeTime(255f, 1f),
        emissionType = EmissionType.FlowEmission(maxParticlesCount = 500),
        durationMillis = 10 * 1000
    )
}

@Composable
fun Confetti() {
    CreateParticles(
        modifier = Modifier
            .fillMaxSize(),
        x = 500f, y = 200f,
        velocity = Velocity(xDirection = 2f, yDirection = -2f, randomize = true),
        force = Force.Gravity(0.3f),
        acceleration = Acceleration(),
        particleSize = ParticleSize.RandomSizes(20..60),
        particleColor = ParticleColor.RandomColors(
            listOf(
                Color.Yellow,
                Color.Blue,
                Color.Red,
                Color.White,
                Color.Magenta,
                Color.Green
            )
        ),
        lifeTime = LifeTime(255f, 2f),
        emissionType = EmissionType.FlowEmission(
            maxParticlesCount = EmissionType.FlowEmission.INDEFINITE,
            emissionRate = 0.8f
        ),
        durationMillis = 10 * 1000
    )
}


@Composable
fun Meteor() {
    CreateParticles(
        modifier = Modifier.fillMaxSize(),
        x = 500f, y = 1200f,
        velocity = Velocity(xDirection = 1f, yDirection = 1f, randomize = true),
        force = Force.Wind(-0.2f, -0.1f),
        acceleration = Acceleration(-1f, -2f),
        particleSize = ParticleSize.ConstantSize(100f),
        particleColor = ParticleColor.SingleColor(Color.White),
        lifeTime = LifeTime(255f, 6f),
        emissionType = EmissionType.FlowEmission(
            maxParticlesCount = EmissionType.FlowEmission.INDEFINITE,
            emissionRate = 1f
        ),
        durationMillis = 10 * 1000
    )
}

@Composable
fun Explosion() {
    CreateParticles(
        modifier = Modifier.fillMaxSize(),
        x = 500f, y = 1000f,
        velocity = Velocity(xDirection = -2f, yDirection = 2f),
        force = Force.Gravity(0.0f),
        acceleration = Acceleration(1f, 1f),
        particleSize = ParticleSize.RandomSizes(10..70),
        particleColor = ParticleColor.RandomColors(
            listOf(
                Color.Yellow,
                Color.Blue,
                Color.Red,
                Color.White,
                Color.Magenta,
                Color.Green
            )
        ),
        lifeTime = LifeTime(255f, 0.5f),
        emissionType = EmissionType.ExplodeEmission(numberOfParticles = 300),
        durationMillis = 10 * 1000
    )
}

@Composable
fun SnowFall() {
    CreateParticles(
        modifier = Modifier.fillMaxSize(),
        x = 500f, y = -50f,
        velocity = Velocity(xDirection = 1f, yDirection = 1f, randomize = true),
        force = Force.Gravity(0.01f),
        acceleration = Acceleration(),
        particleSize = ParticleSize.RandomSizes(10..30),
        particleColor = ParticleColor.SingleColor(Color.White),
        lifeTime = LifeTime(255f, 0.01f),
        emissionType = EmissionType.FlowEmission(maxParticlesCount = 300, emissionRate = 0.5f),
        durationMillis = 10 * 1000
    )
}