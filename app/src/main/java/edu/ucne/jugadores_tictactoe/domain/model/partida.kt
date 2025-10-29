package edu.ucne.jugadores_tictactoe.domain.model

data class Partida(
    val partidaId: Int = 0,
    val fecha: String,
    val jugador1Id: Int,
    val jugador2Id: Int,
    val ganadorId: Int?,
    val esFinalizada: Boolean,
    val currentPlayerId: Int? = null,
    val board: List<Int?> = List(9) { null }
)