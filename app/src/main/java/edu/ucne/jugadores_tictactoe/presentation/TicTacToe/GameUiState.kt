package edu.ucne.jugadores_tictactoe.presentation.TicTacToe

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores

data class GameUiState(
    val board: List<Player?> = List(9) { null },
    val currentPlayer: Player = Player.X,
    val winner: Player? = null,
    val isDraw: Boolean = false,
    val jugadorX: Jugadores? = null,
    val jugadorO: Jugadores? = null,
    val gameStarted: Boolean = false,
    val showPlayerList: Boolean = false,
    val jugadores: List<Jugadores> = emptyList(),
    val isLoadingFromApi: Boolean = false,
    val isSyncingToApi: Boolean = false,
    val apiMessage: String? = null,
    val partidaId: Int = 1
)