package edu.ucne.alainagarcia_ap2_p1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tareas")
data class TareaEntity(
    @PrimaryKey
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0
)