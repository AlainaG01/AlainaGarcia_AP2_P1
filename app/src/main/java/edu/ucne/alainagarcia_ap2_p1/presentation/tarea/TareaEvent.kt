package edu.ucne.alainagarcia_ap2_p1.presentation.tarea

sealed interface TareaEvent{
    data class tareaIdChange(val tareaId: Int): TareaEvent
    data class descripcionChange(val descripcion: String): TareaEvent
    data class tiempoChange(val tiempo: Int): TareaEvent
    data object save: TareaEvent
    data object delete: TareaEvent
    data object new: TareaEvent
}