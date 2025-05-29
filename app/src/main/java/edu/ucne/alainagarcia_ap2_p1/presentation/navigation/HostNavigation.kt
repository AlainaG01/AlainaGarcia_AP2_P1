package edu.ucne.alainagarcia_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.alainagarcia_ap2_p1.presentation.sistema.SistemaListScren

@Composable
fun HostNavigation(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ){
        composable <Screen.List>{
            SistemaListScren()
        }
    }
}