package edu.ucne.alainagarcia_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.alainagarcia_ap2_p1.presentation.tarea.TareaListScreen
import edu.ucne.alainagarcia_ap2_p1.presentation.tarea.TareaScreen

@Composable
fun HostNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ){
        composable <Screen.List>{
            TareaListScreen(
                goToTarea = {id ->
                    navHostController.navigate(Screen.Tarea(id))
                },
                createTarea = {
                    navHostController.navigate(Screen.Tarea(null))
                }
            )
        }

        composable <Screen.Tarea> {backStack ->
            val tareaId = backStack.toRoute<Screen.Tarea>().id
            TareaScreen(
                tareaId = tareaId ?: 0,
                goBack = { navHostController.popBackStack()}
            )
        }
    }
}