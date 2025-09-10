package edu.ucne.jugadores_tictactoe.data.presentation.jugadores


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.text.TextStyle

@Composable
fun JugadorDeleteScreen(
    viewModel: JugadoresViewModel = hiltViewModel(),
    jugadorId:Int,
    goBack: () -> Unit

){

    LaunchedEffect(jugadorId){
        viewModel.selectJugador(jugadorId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeleteJugadorBodyScreen(
        uiState = uiState,
        onDeleteSistema = {
            viewModel.deleteJugador()
            goBack()
        },
        goBack
    )

}


@Composable
fun DeleteJugadorBodyScreen(
    uiState: JugadoresUiState,
    onDeleteSistema:()-> Unit,
    goBack:()-> Unit

){

    Scaffold(
        topBar = {
            Text(
                text = "Estas seguro que deseas eliminar esta tarea?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                ),

                )
        }
    )
    { innerPading ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPading)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)

            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {


                    Text(
                        text = "Jugador ID: ${(uiState.jugadorId)}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )


                    Text(
                        text = "Nombre: ${uiState.nombre}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = "Partida: ${(uiState.partidas)}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onDeleteSistema()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)

            ){
                Text(text = "Eliminar")
            }

            Button(
                onClick = {
                    goBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ){
                Text(text= "Cancelar")
            }

        }

    }

}