package me.nikhilchaudhari.compose_particle_system

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform