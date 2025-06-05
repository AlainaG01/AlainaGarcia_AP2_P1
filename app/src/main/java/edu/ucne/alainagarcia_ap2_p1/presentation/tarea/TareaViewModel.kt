package edu.ucne.alainagarcia_ap2_p1.presentation.tarea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.alainagarcia_ap2_p1.data.local.entities.TareaEntity
import edu.ucne.alainagarcia_ap2_p1.data.repository.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TareaViewModel  @Inject constructor(
    private val tareaRepository: TareaRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TareaEvent){
        when (event) {
            TareaEvent.delete -> delete()
            is TareaEvent.descripcionChange -> onDescripcionChange(event.descripcion)
            TareaEvent.new -> nuevo()
            TareaEvent.save -> viewModelScope.launch { save() }
            is TareaEvent.tareaIdChange -> onTareaIdChange(event.tareaId)
            is TareaEvent.tiempoChange -> onTiempoChange(event.tiempo.toString())
        }
    }

    init {
        getTareas()
    }

    suspend fun save(): Boolean{
        return if(_uiState.value.descripcion.isNullOrBlank()
            || _uiState.value.tiempo <= 0){
            _uiState.update {
                it.copy(errorMessenge = "Campo vacio")
            }
            false
        }else{
            tareaRepository.save(_uiState.value.toEntity())
            true
        }
    }

    private fun nuevo(){
        _uiState.update {
            it.copy(
                tareaId = null,
                descripcion = "",
                tiempo = 0,
                errorMessenge = null
            )
        }
    }

    fun find(tareaId: Int){
        viewModelScope.launch {
            if(tareaId > 0){
                val tarea = tareaRepository.find(tareaId)
                _uiState.update {
                    it.copy(
                        tareaId = tarea?.tareaId,
                        descripcion = tarea?.descripcion ?: "",
                        tiempo = tarea?.tiempo ?: 0
                    )
                }
            }
        }
    }

    private fun delete(){
        viewModelScope.launch {
            tareaRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getTareas(){
        viewModelScope.launch {
            tareaRepository.getAll().collect { tareas ->
                _uiState.update {
                    it.copy(tareas = tareas)
                }
            }
        }
    }

    private fun onTareaIdChange(tareaId: Int){
        _uiState.update {
            it.copy(tareaId = tareaId)
        }
    }

    private fun onDescripcionChange(descripcion: String){
        _uiState.update {
            it.copy(descripcion = descripcion)
        }
    }

    private fun onTiempoChange(tiempo: String?){
        _uiState.update {
            it.copy(tiempo = tiempo?.toInt() ?: 0)
        }
    }

}
fun TareaUiState.toEntity() = TareaEntity(
    tareaId = tareaId,
    descripcion = descripcion ?: "",
    tiempo = tiempo ?: 0
)