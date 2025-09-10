package edu.ucne.jugadores_tictactoe.data.presentation.jugadores


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun JugadorEditScreen(
    jugadorId:Int,
    viewModel: JugadoresViewModel = hiltViewModel(),
    goBack:()->Unit

){

    LaunchedEffect(jugadorId)
    {
        viewModel.selectJugador(jugadorId)

    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    JugadorEditBodyScreen(
        uiState = uiState.value,
        onChangeDescripcion = viewModel::onChangeNombre,
        onChangeTiempo = viewModel::onChangePartidas,
        save = viewModel::saveJugador,
        nuevo = viewModel::nuevoJugador,
        goBack = goBack

    )
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun JugadorEditBodyScreen(
    uiState: JugadoresUiState,
    onChangeDescripcion:(String)->Unit,
    onChangeTiempo: (Int) -> Unit,
    save:()->Unit,
    nuevo:()->Unit,
    goBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Modificar tareas",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White

                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )

        }

    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(16.dp)

        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nombre") },
                value = uiState.nombre,
                onValueChange = onChangeDescripcion

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Partida") },
                value = uiState.partidas.toString(),
                onValueChange = {
                    val partida = it.toIntOrNull() ?: 0
                    onChangeTiempo(partida)
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedButton(
                    onClick = { save()
                        goBack()

                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)

                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Guardar")
                    Text("Guardar")

                }

                OutlinedButton(
                    onClick = { nuevo() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)

                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Nuevo")
                    Text("Nuevo")

                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            uiState.successMessage?.let { menssage ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    content = {
                        Text(
                            text = menssage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Green
                        )
                    }
                )
            }

            uiState.errorMessage?.let { menssage ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    content = {
                        Text(
                            text = menssage,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Red
                        )
                    }

                )
            }
        }

    }
}