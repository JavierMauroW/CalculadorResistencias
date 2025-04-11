package com.example.calculadoraresistencias.Screen



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calculadoraresistencias.Screen.ResultadoScreen
import com.example.calculadoraresistencias.Screen.SelectorScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "selector") {
        composable("selector") {
            SelectorScreen(navController)
        }
        composable("resultado/{resistencia}/{tolerancia}") { backStackEntry ->
            val resistencia = backStackEntry.arguments?.getString("resistencia") ?: "0"
            val tolerancia = backStackEntry.arguments?.getString("tolerancia") ?: "±0%"
            ResultadoScreen(resistencia, tolerancia, navController)
        }

    }
    }

