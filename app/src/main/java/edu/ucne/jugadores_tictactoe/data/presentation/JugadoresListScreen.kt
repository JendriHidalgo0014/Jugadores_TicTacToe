package edu.ucne.jugadores_tictactoe.data.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.jugadores_tictactoe.data.jugadores.local.entities.JugadoresEntity
import edu.ucne.jugadores_tictactoe.data.presentation.jugadores.JugadoresUiState
import edu.ucne.jugadores_tictactoe.data.presentation.jugadores.JugadoresViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun JugadorListScreen(
    viewModel: JugadoresViewModel = hiltViewModel(),
    scope: CoroutineScope,
    onCreate:()-> Unit,
    onEdit:(Int)-> Unit,
    onDelete:(Int)-> Unit

){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    JugadorBodyListScreen(
        uiState = uiState,
        scope = scope,
        onCreate = onCreate,
        onEdit = onEdit,
        onDelete = onDelete
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JugadorBodyListScreen(
    uiState: JugadoresUiState,
    scope: CoroutineScope,
    onCreate:()-> Unit,
    onEdit:(Int)-> Unit,
    onDelete:(Int)-> Unit

){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lista de jugadores",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {onCreate()},
                modifier = Modifier.padding(vertical = 8.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.White

            ){
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }

    ){innerPadding->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize()

        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ){
                items(uiState.jugadores){jugador->
                    JugadorRow(jugador,onEdit,onDelete)
                }
            }
        }
    }
}

@Composable
fun JugadorRow(
    jugador: JugadoresEntity,
    onEdit: (Int) -> Unit,
    onDelete: (Int) -> Unit

) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                modifier = Modifier.weight(5f),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Jugador Id: ${jugador.jugadorId}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface

                    )

                )

                Text(
                    text = "Nombre: ${jugador.nombre}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface

                    )

                )

                Text(
                    text = "Partidas: ${jugador.partidas}",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                )
            }

            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.weight(1f)

            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = "Mas opciones")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)

            ) {
                DropdownMenuItem(
                    text = { Text("Editar") },
                    leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Editar") },
                    onClick = {
                        expanded = false
                        onEdit(jugador.jugadorId!!)
                    }
                )


                DropdownMenuItem(
                    text = { Text("Eliminar") },
                    leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = "Eliminar") },
                    onClick = {
                        expanded = false
                        onDelete(jugador.jugadorId!!)
                    }
                )
            }

            }
        }
}