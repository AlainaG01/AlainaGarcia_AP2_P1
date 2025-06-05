package edu.ucne.alainagarcia_ap2_p1.presentation.tarea

import edu.ucne.alainagarcia_ap2_p1.data.local.entities.TareaEntity

data class TareaUiState(
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0,
    val errorMessenge: String? = null,
    val tareas: List<TareaEntity> = emptyList()
)