package edu.ucne.jugadores_tictactoe.presentation.TicTacToe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeScreen(
    onDrawer: () -> Unit = {},
    viewModel: GameViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectingFor by remember { mutableStateOf<Player?>(null) }

    TicTacToeBody(
        state = state,
        onSelectX = {
            selectingFor = Player.X
            viewModel.showPlayerSelection()
        },
        onSelectO = {
            selectingFor = Player.O
            viewModel.showPlayerSelection()
        },
        startGame = { viewModel.startGame() },
        onCellClick = { index -> viewModel.onCellClick(index) },
        restartGame = { viewModel.restartGame() },
        onLoadFromApi = { viewModel.loadGameFromApi() },
        onSyncToApi = { viewModel.syncGameToApi() },
        onClearMessage = { viewModel.clearApiMessage() }
    )

    if (state.showPlayerList && selectingFor != null) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                viewModel.hidePlayerList()
                selectingFor = null
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Selecciona jugador para ${selectingFor?.symbol}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(16.dp))

                state.jugadores.forEach { jugador ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (selectingFor == Player.X) {
                                    viewModel.selectPlayerForX(jugador)
                                } else {
                                    viewModel.selectPlayerForO(jugador)
                                }
                                viewModel.hidePlayerList()
                                selectingFor = null
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(jugador.nombre, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}

@Composable
private fun TicTacToeBody(
    state: GameUiState,
    onSelectX: () -> Unit,
    onSelectO: () -> Unit,
    startGame: () -> Unit,
    onCellClick: (Int) -> Unit,
    restartGame: () -> Unit,
    onLoadFromApi: () -> Unit,
    onSyncToApi: () -> Unit,
    onClearMessage: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!state.gameStarted) {
                Text("Elige tus jugadores", fontSize = 28.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Jugador X: ${state.jugadorX?.nombre ?: "Sin seleccionar"}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Button(onClick = onSelectX) {
                    Text("Seleccionar Jugador para X")
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Jugador O: ${state.jugadorO?.nombre ?: "Sin seleccionar"}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Button(onClick = onSelectO) {
                    Text("Seleccionar Jugador para O")
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = startGame,
                    enabled = state.jugadorX != null && state.jugadorO != null
                ) {
                    Text("Iniciar Partida", fontSize = 18.sp)
                }
            } else {
                GameBoard(
                    uiState = state,
                    onCellClick = onCellClick,
                    onRestartGame = restartGame,
                    onLoadFromApi = onLoadFromApi,
                    onSyncToApi = onSyncToApi,
                    onClearMessage = onClearMessage
                )
            }
        }
    }
}

@Composable
fun GameBoard(
    uiState: GameUiState,
    onCellClick: (Int) -> Unit,
    onRestartGame: () -> Unit,
    onLoadFromApi: () -> Unit,
    onSyncToApi: () -> Unit,
    onClearMessage: () -> Unit
) {
    val gameStatus = when {
        uiState.winner != null -> {
            val ganador = if (uiState.winner == Player.X) uiState.jugadorX else uiState.jugadorO
            "¡El ganador es!: ${ganador?.nombre}!"
        }
        uiState.isDraw -> "¡Es un empate!"
        else -> {
            val turno = if (uiState.currentPlayer == Player.X) uiState.jugadorX else uiState.jugadorO
            "Tu Turno: ${turno?.nombre}"
        }
    }

    Text(text = gameStatus, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(20.dp))

    GameBoardBody(board = uiState.board, onCellClick = onCellClick)

    Spacer(modifier = Modifier.height(20.dp))

    if (uiState.apiMessage != null) {
        Text(
            text = uiState.apiMessage,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LaunchedEffect(uiState.apiMessage) {
            delay(3000)
            onClearMessage()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onLoadFromApi,
            enabled = !uiState.isLoadingFromApi && !uiState.isSyncingToApi,
            modifier = Modifier.weight(1f)
        ) {
            if (uiState.isLoadingFromApi) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("🔄 Cargar", fontSize = 14.sp)
            }
        }

        Button(
            onClick = onSyncToApi,
            enabled = !uiState.isLoadingFromApi && !uiState.isSyncingToApi,
            modifier = Modifier.weight(1f)
        ) {
            if (uiState.isSyncingToApi) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("☁️ Guardar", fontSize = 14.sp)
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(onClick = onRestartGame, modifier = Modifier.fillMaxWidth()) {
        Text("🔄 Reiniciar Juego", fontSize = 18.sp)
    }
}

@Composable
fun GameBoardBody(board: List<Player?>, onCellClick: (Int) -> Unit) {
    Column {
        (0..2).forEach { row ->
            Row {
                (0..2).forEach { col ->
                    val index = row * 3 + col
                    BoardCell(board[index]) {
                        onCellClick(index)
                    }
                }
            }
        }
    }
}

@Composable
private fun BoardCell(
    player: Player?,
    onCellClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .background(Color.LightGray)
            .clickable { onCellClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player?.symbol ?: "",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = if (player == Player.X) Color.Blue else Color.Red
        )
    }
}