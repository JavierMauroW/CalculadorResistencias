package com.example.calculadoraresistencias.Screen





import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculadoraresistencias.Screen.Resistor


@Composable
fun SelectorScreen(navController: NavController) {
    val colores = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo", "Verde", "Azul", "Violeta", "Gris", "Blanco")
    val multiplicadores = listOf("Negro", "Marrón", "Rojo", "Naranja", "Amarillo")
    val tolerancias = listOf("Dorado", "Plateado", "Ninguno")

    var banda1 by remember { mutableStateOf("") }
    var banda2 by remember { mutableStateOf("") }
    var banda3 by remember { mutableStateOf("") }
    var banda4 by remember { mutableStateOf("") }

    val valores = mapOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3, "Amarillo" to 4,
        "Verde" to 5, "Azul" to 6, "Violeta" to 7, "Gris" to 8, "Blanco" to 9
    )

    val multiplicadoresValores = mapOf(
        "Negro" to 1, "Marrón" to 10, "Rojo" to 100, "Naranja" to 1000, "Amarillo" to 10000
    )

    val toleranciaValores = mapOf(
        "Dorado" to "±5%", "Plateado" to "±10%", "Ninguno" to "±20%"
    )

    val todoSeleccionado = banda1.isNotEmpty() && banda2.isNotEmpty() && banda3.isNotEmpty() && banda4.isNotEmpty()

    val fondoGradiente = Brush.verticalGradient(
        listOf(Color(0xFFB3E5FC), Color(0xFFE1F5FE))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGradiente)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Calculadora de Resistencias",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF01579B)
            )

            CardSelector("Banda 1", colores, banda1) { banda1 = it }
            CardSelector("Banda 2", colores, banda2) { banda2 = it }
            CardSelector("Multiplicador", multiplicadores, banda3) { banda3 = it }
            CardSelector("Tolerancia", tolerancias, banda4) { banda4 = it }


            if (banda1.isNotEmpty() && banda2.isNotEmpty() && banda3.isNotEmpty()) {
                Resistor(banda1, banda2, banda3)
            }

            Button(
                onClick = {
                    val valor = ((valores[banda1] ?: 0) * 10 + (valores[banda2] ?: 0)) * (multiplicadoresValores[banda3] ?: 1)
                    val tolerancia = toleranciaValores[banda4] ?: "±0%"
                    val ruta = "resultado/${valor}/${Uri.encode(tolerancia)}"
                    navController.navigate(ruta)
                },
                enabled = todoSeleccionado,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (todoSeleccionado) Color(0xFF0288D1) else Color.Gray,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Calcular resistencia", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun CardSelector(label: String, opciones: List<String>, seleccion: String, onSeleccionar: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontWeight = FontWeight.SemiBold, color = Color(0xFF0288D1))
            Spacer(modifier = Modifier.height(8.dp))
            Box {
                TextButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (seleccion.isEmpty()) "Seleccionar..." else seleccion,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    opciones.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                onSeleccionar(opcion)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
