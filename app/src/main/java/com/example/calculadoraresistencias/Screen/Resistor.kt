package com.example.calculadoraresistencias.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Resistor(banda1: String?, banda2: String?, banda3: String?) {
    val colorMap = mapOf(
        "Negro" to Color.Black,
        "Marron" to Color(0xFF8B4513),
        "Rojo" to Color.Red,
        "Naranja" to Color(0xFFFFA500),
        "Amarillo" to Color.Yellow,
        "Verde" to Color.Green,
        "Azul" to Color.Blue,
        "Violeta" to Color(0xFF8A2BE2),
        "Gris" to Color.Gray,
        "Blanco" to Color.White,
        "Dorado" to Color(0xFFFFD700),
        "Plateado" to Color.LightGray
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(60.dp)
            .background(Color(0xFFDEB887), shape = RoundedCornerShape(30.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        banda1?.let {
            Box(modifier = Modifier
                .width(8.dp)
                .height(50.dp)
                .background(colorMap[it] ?: Color.Black)
            )
        }
        banda2?.let {
            Box(modifier = Modifier
                .width(8.dp)
                .height(50.dp)
                .background(colorMap[it] ?: Color.Black)
            )
        }
        banda3?.let {
            Box(modifier = Modifier
                .width(8.dp)
                .height(50.dp)
                .background(colorMap[it] ?: Color.Black)
            )
        }
    }
}
