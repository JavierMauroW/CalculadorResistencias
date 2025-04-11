package com.example.calculadoraresistencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.calculadoraresistencias.Screen.NavGraph
import com.example.calculadoraresistencias.ui.theme.CalculadoraResistenciasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraResistenciasTheme {
                NavGraph()
            }
        }
    }
}