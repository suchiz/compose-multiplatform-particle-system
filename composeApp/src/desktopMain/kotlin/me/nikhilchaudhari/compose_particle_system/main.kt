package me.nikhilchaudhari.compose_particle_system

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "compose-multiplatform-particle-system",
    ) {
        App()
    }
}