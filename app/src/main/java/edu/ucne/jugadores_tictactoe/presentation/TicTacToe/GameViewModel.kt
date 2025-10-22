package edu.ucne.jugadores_tictactoe.presentation.TicTacToe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jugadores_tictactoe.domain.Apiusecase.Movimientousecase.GetMovimientoUseCase
import edu.ucne.jugadores_tictactoe.domain.Apiusecase.Movimientousecase.PostMovimientoUseCase
import edu.ucne.jugadores_tictactoe.domain.model.Jugadores
import edu.ucne.jugadores_tictactoe.domain.model.Movimiento
import edu.ucne.jugadores_tictactoe.domain.usecase.GetAllJugadoresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getAllJugadoresUseCase: GetAllJugadoresUseCase,
    private val getMovimientoUseCase: GetMovimientoUseCase,
    private val postMovimientoUseCase: PostMovimientoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(GameUiState())
    val state: StateFlow<GameUiState> = _state.asStateFlow()

    fun showPlayerSelection() {
        viewModelScope.launch {
            getAllJugadoresUseCase().collect { jugadores ->
                _state.update {
                    it.copy(
                        showPlayerList = true,
                        jugadores = jugadores
                    )
                }
            }
        }
    }

    fun selectPlayerForX(jugador: Jugadores) {
        _state.update { it.copy(jugadorX = jugador) }
    }

    fun selectPlayerForO(jugador: Jugadores) {
        _state.update { it.copy(jugadorO = jugador) }
    }

    fun startGame() {
        val jugadorX = _state.value.jugadorX
        val jugadorO = _state.value.jugadorO

        if (jugadorX != null && jugadorO != null) {
            _state.update { it.copy(gameStarted = true) }
        }
    }

    fun onCellClick(index: Int) {
        if (_state.value.board[index] != null || _state.value.winner != null) return

        val newBoard = _state.value.board.toMutableList()
        newBoard[index] = _state.value.currentPlayer

        val newWinner = checkWinner(newBoard)
        val isDraw = newBoard.all { it != null } && newWinner == null

        _state.update {
            it.copy(
                board = newBoard,
                currentPlayer = if (it.currentPlayer == Player.X) Player.O else Player.X,
                winner = newWinner,
                isDraw = isDraw
            )
        }
    }

    fun hidePlayerList() {
        _state.update { it.copy(showPlayerList = false) }
    }

    fun restartGame() {
        _state.value = GameUiState(
            jugadorX = _state.value.jugadorX,
            jugadorO = _state.value.jugadorO,
            gameStarted = true,
            partidaId = _state.value.partidaId
        )
    }

    // 🆕 Cargar estado desde la API
    fun loadGameFromApi() {
        viewModelScope.launch {
            _state.update { it.copy(isLoadingFromApi = true, apiMessage = null) }
            try {
                val movimientos = getMovimientoUseCase(_state.value.partidaId)

                if (movimientos.isNotEmpty()) {
                    val newBoard = MutableList<Player?>(9) { null }

                    // Filtrar movimientos válidos y sin duplicados
                    val movimientosValidos = movimientos
                        .filter { it.posicionFila in 0..2 && it.posicionColumna in 0..2 }
                        .distinctBy { "${it.posicionFila}-${it.posicionColumna}" }

                    movimientosValidos.forEach { movimiento ->
                        val index = movimiento.posicionFila * 3 + movimiento.posicionColumna

                        val player = when (movimiento.jugador.uppercase()) {
                            "X" -> Player.X
                            "O" -> Player.O
                            else -> null
                        }

                        if (index in 0..8) {
                            newBoard[index] = player
                        }
                    }

                    val newWinner = checkWinner(newBoard)
                    val isDraw = newBoard.all { it != null } && newWinner == null

                    // Determinar turno actual
                    val xCount = newBoard.count { it == Player.X }
                    val oCount = newBoard.count { it == Player.O }
                    val currentPlayer = if (xCount > oCount) Player.O else Player.X

                    _state.update {
                        it.copy(
                            board = newBoard,
                            currentPlayer = currentPlayer,
                            winner = newWinner,
                            isDraw = isDraw,
                            isLoadingFromApi = false,
                            apiMessage = "✅ Cargados ${movimientosValidos.size} movimientos"
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoadingFromApi = false,
                            apiMessage = "ℹ️ No hay movimientos en el servidor"
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoadingFromApi = false,
                        apiMessage = "❌ Error: ${e.message}"
                    )
                }
            }
        }
    }

    // 🆕 Guardar estado en la API
    fun syncGameToApi() {
        viewModelScope.launch {
            _state.update { it.copy(isSyncingToApi = true, apiMessage = null) }
            try {
                val currentState = _state.value
                val board = currentState.board
                var movimientosEnviados = 0

                board.forEachIndexed { index, player ->
                    if (player != null) {
                        val fila = index / 3
                        val columna = index % 3

                        val movimiento = Movimiento(
                            partidaId = currentState.partidaId,
                            jugador = player.symbol,
                            posicionFila = fila,
                            posicionColumna = columna
                        )

                        postMovimientoUseCase(movimiento)
                        movimientosEnviados++
                    }
                }

                _state.update {
                    it.copy(
                        isSyncingToApi = false,
                        apiMessage = "✅ Enviados $movimientosEnviados movimientos"
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isSyncingToApi = false,
                        apiMessage = "❌ Error: ${e.message}"
                    )
                }
            }
        }
    }

    fun clearApiMessage() {
        _state.update { it.copy(apiMessage = null) }
    }

    private fun checkWinner(board: List<Player?>): Player? {
        val winningLines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
            listOf(0, 4, 8), listOf(2, 4, 6)
        )

        for (line in winningLines) {
            val (a, b, c) = line
            if (board[a] != null && board[a] == board[b] && board[a] == board[c]) {
                return board[a]
            }
        }
        return null
    }
}

enum class Player(val symbol: String) {
    X("X"),
    O("O")
}