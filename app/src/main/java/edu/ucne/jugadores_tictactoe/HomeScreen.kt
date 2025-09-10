package edu.ucne.jugadores_tictactoe

import androidx.benchmark.perfetto.PerfettoConfig
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onAddJugadorClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Lista de Jugadores") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddJugadorClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar jugador")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Pantalla principal")
        }
    }
}
