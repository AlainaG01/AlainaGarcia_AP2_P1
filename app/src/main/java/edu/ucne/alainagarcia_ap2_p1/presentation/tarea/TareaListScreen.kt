package edu.ucne.alainagarcia_ap2_p1.presentation.tarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.alainagarcia_ap2_p1.data.local.entities.TareaEntity

@Composable
fun TareaListScreen(
    viewModel: TareaViewModel = hiltViewModel(),
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit,
    delete: ((TareaEntity) -> Unit)? = null
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TareaBodyListScreen(
        uiState = uiState,
        goToTarea = goToTarea,
        createTarea = createTarea,
        delete = { tarea ->
            viewModel.onEvent(TareaEvent.tareaIdChange(tarea.tareaId ?: 0))
            viewModel.onEvent(TareaEvent.delete)
        }
    )
}

@Composable
private fun TareaRow(
    tarea: TareaEntity,
    goToTarea: () -> Unit,
    delete: (TareaEntity) -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = tarea.tareaId.toString(),
            color = Color.Black
        )

        Text(
            modifier = Modifier.weight(2f),
            text = tarea.descripcion,
            color = Color.Black
        )

        Text(
            modifier = Modifier.weight(2f),
            text =  "${tarea.tiempo.toString()} min" ,
            color = Color.Black
        )

        IconButton(
            onClick = goToTarea
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Editar",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        IconButton(
            onClick = {delete(tarea)}
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaBodyListScreen(
    uiState: TareaUiState,
    goToTarea: (Int) -> Unit,
    createTarea: () -> Unit,
    delete: (TareaEntity) -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {Text("  Lista de Tareas")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createTarea
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "crear"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(uiState.tareas) { tarea ->
                    TareaRow(
                        tarea = tarea,
                        goToTarea = { goToTarea(tarea.tareaId ?: 0)},
                        delete = delete
                    )
                }
            }
        }
    }
}