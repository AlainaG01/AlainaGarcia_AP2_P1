package edu.ucne.alainagarcia_ap2_p1.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{
    @Serializable
    data object List: Screen()
    @Serializable
    data class Tarea(val id: Int?): Screen()
}