package edu.ucne.jugadores_tictactoe.presentation.Jugadortareas

import edu.ucne.jugadores_tictactoe.domain.model.Jugadores

data class JugadorUiState(
    val isLoading: Boolean = false,
    val jugadores: List<Jugadores> = emptyList(),
    val userMessage: String? = null,
    val showCreateSheet: Boolean = false,
    val jugadorDescription: String = ""
) {
    companion object {
        fun default() = JugadorUiState()
    }
}